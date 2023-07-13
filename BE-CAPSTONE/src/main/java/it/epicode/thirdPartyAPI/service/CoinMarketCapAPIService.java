package it.epicode.thirdPartyAPI.service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.epicode.entities.crypto.service.CurrentCryptoDataService;
import it.epicode.entities.crypto.service.FakeCurrentCryptoDataService;

@Service
public class CoinMarketCapAPIService {

	private static String cmcApiKey;

	@Value("${spring.application.cmcApiKey}")
	public void setCmcApiKey(String apiKey) {
		cmcApiKey = apiKey;
	}

	@Autowired
	CurrentCryptoDataService cryptoService;

	@Autowired
	FakeCurrentCryptoDataService fakeCryptoService;

	public void getRequest() throws JsonMappingException, JsonProcessingException {

		List<String> listaSimboli = Arrays.asList("BTC", "ETH", "ADA", "DOT", "MATIC", "XRP", "DOGE", "SAND");

		for (String symbol : listaSimboli) {
			String apiUrl = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=" + symbol
					+ "&CMC_PRO_API_KEY=" + cmcApiKey;

			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

			RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiUrl));

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

			String responseBody = response.getBody();
			HttpStatusCode statusCode = response.getStatusCode();

			System.out.println("Status code: " + statusCode);
			System.out.println("Response body: " + responseBody);
			System.out.println("Crypto: " + symbol);

			processJSonData(response.getBody(), symbol);
		}
	}

	public void processJSonData(String json, String crypto) throws JsonMappingException, JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(json);

		JsonNode dataNode = rootNode.get("data");
		JsonNode cryptoNode = dataNode.get(crypto);

		String name = cryptoNode.get("name").asText();
		String symbol = cryptoNode.get("symbol").asText();

		Map<String, Object> quoteMap = objectMapper.convertValue(cryptoNode.get("quote"),
				new TypeReference<Map<String, Object>>() {
				});
		Map<String, Object> usdMap = (Map<String, Object>) quoteMap.get("USD");

		double price = Double.valueOf(usdMap.get("price").toString());
		double percent_change_1h = Double.valueOf(usdMap.get("percent_change_1h").toString());

		price = Math.round(price * 10000) / 10000.0;
		percent_change_1h = Math.round(percent_change_1h * 100000) / 100000.0;

		String percentualeFormatted = (percent_change_1h >= 0) ? "+" + percent_change_1h
				: String.valueOf(percent_change_1h);

		// String percentualeFormattata = String.valueOf(percent_change_1h);

		cryptoService.create(symbol, name, price, percent_change_1h);
		fakeCryptoService.create(symbol, name, price, percentualeFormatted);

	}
}
