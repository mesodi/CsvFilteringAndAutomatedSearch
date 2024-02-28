package es.wacoco.csvfilteringandautomatedsearch.Camel.routes;

import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.*;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import es.wacoco.csvfilteringandautomatedsearch.service.JobService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProcessorRoute extends RouteBuilder {

    private final JobService jobService;

    public ProcessorRoute(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public void configure() {

        from("direct:csvFilteringRoute")
                .log("Starting CSV file processing")
                .process(new FilterCsvProcessor())
                .log("Finished processing CSV file");


        from("direct:processSelected")
                .process(exchange -> {
                    String jobId = exchange.getIn().getHeader("jobId", String.class);
                    Job job = jobService.getJob(jobId);
                    exchange.getIn().setBody(job);
                })
                .process(new LinkedInUrlFinderProcessor())
                .process(new WebsiteUrlFinderProcessor())
                .process(new EmailExtractorProcessor())

                .process(exchange -> {
                    Job job = exchange.getIn().getBody(Job.class);
                    job.setCurrentStatus(Job.Status.DONE);
                    jobService.updateJob(job);
                });

        from("direct:exportLinkedinUrlAsCsvRoute")
                .process(new ExportLinkedinUrlAsCsvProcessor());


    }


}
