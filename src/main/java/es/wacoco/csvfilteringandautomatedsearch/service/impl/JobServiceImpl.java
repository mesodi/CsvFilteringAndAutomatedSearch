package es.wacoco.csvfilteringandautomatedsearch.service.impl;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import es.wacoco.csvfilteringandautomatedsearch.service.JobService;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
@Service
public class JobServiceImpl implements JobService {
    private final Map<String, Job> jobs = new ConcurrentHashMap<>();
    private final ProducerTemplate producerTemplate;

    @Autowired
    public JobServiceImpl(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @Override
    public Job createAndProcessJob(List<Company> companies) {
        LocalDateTime now = LocalDateTime.now();
        String jobId = createJobID(now);
        Job job = new Job(jobId, now.format(DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm")), Job.Status.PROCESSING, companies);
        jobs.put(job.getJobID(), job);

        producerTemplate.sendBodyAndHeader("direct:processSelected", companies, "jobId", jobId);

        return job;
    }

    @Override
    public Job getJob(String jobId) {
        return jobs.get(jobId);
    }

    @Override
    public void updateJob(Job job) {
        jobs.put(job.getJobID(), job);
    }

    @Override
    public List<Job> getAllJobs() {
        return new ArrayList<>(jobs.values()).stream()
                .sorted(Comparator.comparing(Job::getJobID).reversed())
                .collect(Collectors.toList());
    }

    private String createJobID(LocalDateTime dateTime) {
        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        String uniqueKey = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return "QRY-" + formattedDate + "-" + uniqueKey;
    }

}

