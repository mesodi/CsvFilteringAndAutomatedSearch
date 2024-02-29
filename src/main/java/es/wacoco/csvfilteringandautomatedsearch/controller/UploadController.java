package es.wacoco.csvfilteringandautomatedsearch.controller;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import es.wacoco.csvfilteringandautomatedsearch.service.CompanyService;
import es.wacoco.csvfilteringandautomatedsearch.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
//http://localhost:8080/swagger-ui.html
@Slf4j
public class UploadController {
    private final JobService jobService;

    private final CompanyService companyService;
    private final ProducerTemplate producerTemplate;


    public UploadController(JobService jobService, CompanyService companyService, ProducerTemplate producerTemplate) {
        this.jobService = jobService;
        this.companyService = companyService;
        this.producerTemplate = producerTemplate;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            producerTemplate.sendBody("direct:csvFilteringRoute", file);
            log.info("File uploaded successfully: {}", file.getOriginalFilename());
            return "File uploaded successfully";
        } catch (Exception e) {
            log.error("Error uploading file: {}", e.getMessage(), e);
            return "Error uploading file: " + e.getMessage();
        }
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


    @Operation(summary = "Get All Inventor URLs with Job ID", description = "Retrieves all inventor URLs with corresponding job IDs")
    @GetMapping("/inventor/all-linkedin")
    public void getAllInventorUrlsWithJobId(HttpServletResponse response) {
        String csvContent = producerTemplate.requestBody("direct:exportLinkedInUrls", null, String.class);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"LinkedInUrls.csv\"");

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(csvContent.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("Error streaming CSV content", e);
        }
    }
}
