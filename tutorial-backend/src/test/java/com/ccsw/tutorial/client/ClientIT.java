package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.ClientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ClientService clientService;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/client";
    }

    @Test
    public void shouldCreateClientSuccessfully() {
        RestTemplate restTemplate = new RestTemplate();

        ClientDto clientDto = new ClientDto();
        clientDto.setName("Nuevo Cliente");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ClientDto> request = new HttpEntity<>(clientDto, headers);

        // 💥 AQUÍ CAMBIAMOS postForEntity → exchange y usamos HttpMethod.PUT
        ResponseEntity<Void> response = restTemplate.exchange(getBaseUrl(), HttpMethod.PUT, request, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        boolean exists = clientService.findAll().stream().anyMatch(client -> client.getName().equals("Nuevo Cliente"));

        assertTrue(exists);
    }

}
