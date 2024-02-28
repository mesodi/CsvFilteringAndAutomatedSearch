package es.wacoco.csvfilteringandautomatedsearch.controller;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {
    private ProducerTemplate producerTemplate;
    @Operation(summary = "Create a New Job", description = "Create a new job for processing a list of companies")
    @PostMapping("/createJob")
    public String createJob(@RequestBody List<Company> companies){
        producerTemplate.sendBody("direct:processManager", companies);
        return "Job Created";
    }



}
