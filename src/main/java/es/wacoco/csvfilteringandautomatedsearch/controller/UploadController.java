package es.wacoco.csvfilteringandautomatedsearch.controller;

import es.wacoco.csvfilteringandautomatedsearch.database.Database;
import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.InventorUrl;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import es.wacoco.csvfilteringandautomatedsearch.service.CompanyService;
import es.wacoco.csvfilteringandautomatedsearch.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/swagger-ui.html
@Slf4j
public class UploadController {
    private final JobService jobService;

    private final CompanyService companyService;

    public UploadController(JobService jobService, CompanyService companyService) {
        this.jobService = jobService;
        this.companyService = companyService;
    }
    @Operation(summary = "Upload CSV File", description = "Uploads a CSV file for filtering")
    @PostMapping("/process-selected")
    public ResponseEntity<Job> processSelectedCompanies(@RequestBody List<Company> selectedCompanies) {
        Job job = jobService.createAndProcessJob(selectedCompanies);
        return ResponseEntity.ok(job);
    }
    @Operation(summary = "Get All Companies", description = "Retrieves all companies")
    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/inventor/{jobId}")
    public ResponseEntity<List<InventorUrl>> getInventorUrlsForJob(@PathVariable String jobId) {
        List<InventorUrl> inventorUrls = Database.getInventorUrlsForJob(jobId);
        if (inventorUrls.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inventorUrls);
    }
    @GetMapping("/inventor/all-linkedin")
    public ResponseEntity<List<InventorUrl>> getAllInventorUrlsWithJobId() {
        List<InventorUrl> inventorUrlsWithJobId = Database.getAllInventorUrlsWithJobId();
        return ResponseEntity.ok(inventorUrlsWithJobId);
    }
}
