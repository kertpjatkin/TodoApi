package in.pjatk.todoapi.application.controllers;

import static in.pjatk.todoapi.application.controllers.helpers.ResponseUtil.buildOkResponse;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping
    public ResponseEntity<Map<String, String>> get() {
        return buildOkResponse(ImmutableMap.of("status", "OK"));
    }

}
