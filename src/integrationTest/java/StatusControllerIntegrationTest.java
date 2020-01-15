package integ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.pjatk.todoapi.TodoApiApplication;
import java.util.Map;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = TodoApiApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
public class StatusControllerIntegrationTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @SneakyThrows
    public void whenServerIsRunning_returnsStatusOK() {
        var response = this.restTemplate
            .getForEntity("http://localhost:" + port + "/", String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(OBJECT_MAPPER.readValue(response.getBody(), Map.class).get("status"), "OK");
    }
}
