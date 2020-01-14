package in.pjatk.todoapi.application.controllers;

import static in.pjatk.todoapi.application.controllers.helpers.ResponseUtil.buildOkResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping
    public ResponseEntity<String> get() {
        return buildOkResponse("OK");
    }

}
