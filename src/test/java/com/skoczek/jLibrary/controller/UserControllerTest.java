package com.skoczek.jLibrary.controller;

import com.skoczek.jLibrary.configuration.JLibrary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JLibrary.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class UserControllerTest {

    @LocalServerPort
    protected int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void shouldReturnUnathorized() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/users/1"), HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}