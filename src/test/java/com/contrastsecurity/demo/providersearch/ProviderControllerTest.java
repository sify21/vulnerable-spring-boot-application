package com.contrastsecurity.demo.providersearch;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProviderControllerTest {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ProviderController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testProviderSearch() {
		assertThat(
				this.restTemplate
						.getForObject("http://localhost:" + port + "/", String.class))
				.contains("Provider Search");

		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("zipCode", "21230");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		String response = this.restTemplate.postForObject("http://localhost:" + port + "/", request, String.class);

		// These two are public
		assertThat(response).contains("Stephen");
		assertThat(response).contains("Jane");

		// This one is not
		assertThat(response).doesNotContain("Alfred");
	}
}
