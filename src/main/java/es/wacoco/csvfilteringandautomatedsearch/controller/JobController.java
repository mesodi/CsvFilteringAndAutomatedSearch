package es.wacoco.csvfilteringandautomatedsearch.controller;

import es.wacoco.csvfilteringandautomatedsearch.repository.Repository;
import es.wacoco.csvfilteringandautomatedsearch.model.InventorUrl;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import es.wacoco.csvfilteringandautomatedsearch.model.JobStatus;
import es.wacoco.csvfilteringandautomatedsearch.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    @Operation(summary = "Get All Jobs", description = "Returns a list of all available jobs")
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        if (jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jobs);
    }
    @Operation(summary ="Get Job by ID",description = "Returns the details of a job specified by its unique ID")
    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable String jobId) {
        Job job = jobService.getJob(jobId);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(job);
    }

    @Operation(summary = "Get Inventor URLs for Job", description = "Retrieves inventor URLs for a specific job")
    @GetMapping("/inventor/{jobId}")
    public ResponseEntity<List<InventorUrl>> getInventorUrlsForJob(@PathVariable String jobId) {
        List<InventorUrl> inventorUrls = Repository.getInventorUrlsForJob(jobId);
        if (inventorUrls.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inventorUrls);
    }
@Operation(summary = "Get Job Status", description = "Returns the current status of a job specified by unique ID")
    @GetMapping("/jobs/{jobId}/status")
    public ResponseEntity<JobStatus> getJobStatus(@PathVariable String jobId) {
        Job job = jobService.getJob(jobId);
        if (job != null) {
            return ResponseEntity.ok(job.getCurrentStatus());
        }
        return ResponseEntity.notFound().build();
    }

}
