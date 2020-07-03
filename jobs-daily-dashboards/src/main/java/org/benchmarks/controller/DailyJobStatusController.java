package org.benchmarks.controller;

import org.benchmarks.service.DailyJobStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "dailyJobStatus")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class DailyJobStatusController {

    @Autowired
    private DailyJobStatusService dailyJobStatusService;

    @PostMapping(value = "run-status")
    public ResponseEntity<Void> runStatus() {
        HttpHeaders headers = new HttpHeaders();
        if (dailyJobStatusService.runStatus()) {
            return new ResponseEntity<Void>(headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(headers, HttpStatus.NOT_FOUND);
        }
    }
}
