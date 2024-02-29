package es.wacoco.csvfilteringandautomatedsearch.service;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;

import java.util.List;

public interface JobService {
    Job createAndProcessJob(List<Company> companies);
    Job getJob(String jobId);
    void updateJob(Job job);
    List<Job> getAllJobs();
}
