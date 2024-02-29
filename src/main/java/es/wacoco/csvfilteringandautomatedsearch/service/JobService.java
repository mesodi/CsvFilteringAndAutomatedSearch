package es.wacoco.csvfilteringandautomatedsearch.service;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JobService {

    private final Map<String, Job> jobs = new ConcurrentHashMap<>();
    private final ProducerTemplate producerTemplate;

    @Autowired
    public JobService(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public Job createAndProcessJob(List<Company> companies) {
        LocalDateTime now = LocalDateTime.now();
        String jobId = createJobID(now);
        Job job = new Job(jobId, now.format(DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm")), Job.Status.PROCESSING, companies);
        jobs.put(job.getJobID(), job);


        producerTemplate.sendBodyAndHeader("direct:processSelected", companies, "jobId", jobId);

        return job;
    }

    private String createJobID(LocalDateTime dateTime) {
        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String uniqueKey = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "QRY-" + formattedDate + "-" + uniqueKey;
    }

    public Job getJob(String jobId) {
        return jobs.get(jobId);
    }

    public void updateJob(Job job) {
        jobs.put(job.getJobID(), job);
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobs.values());
    }
}
