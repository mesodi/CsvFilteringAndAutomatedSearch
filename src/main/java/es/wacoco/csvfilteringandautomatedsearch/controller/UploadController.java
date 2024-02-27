package es.wacoco.csvfilteringandautomatedsearch.controller;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
public class UploadController {
    private final ProducerTemplate producerTemplate;

    private final CompanyService companyService;

    public UploadController(ProducerTemplate producerTemplate, CompanyService companyService) {
        this.producerTemplate = producerTemplate;
        this.companyService = companyService;
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

    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
}
