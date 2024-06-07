package com.frknydin.librarym.management.controller;

import com.frknydin.librarym.management.request.GetTestRequest;
import com.frknydin.librarym.management.response.GetTestResponse;
import com.frknydin.librarym.management.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/test")
    public ResponseEntity<GetTestResponse> getUser(@RequestBody GetTestRequest request){
        GetTestResponse getTestResponse = testService.testService(request);
        return ResponseEntity.ok(getTestResponse);
    }
}
