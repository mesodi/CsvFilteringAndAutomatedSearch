package es.wacoco.csvfilteringandautomatedsearch.Camel.routes;

import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.*;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import es.wacoco.csvfilteringandautomatedsearch.model.JobStatus;
import es.wacoco.csvfilteringandautomatedsearch.service.impl.JobServiceImpl;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProcessorRoute extends RouteBuilder {

    private final JobServiceImpl jobServiceImpl;

    public ProcessorRoute(JobServiceImpl jobServiceImpl) {
        this.jobServiceImpl = jobServiceImpl;
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
                    Job job = jobServiceImpl.getJob(jobId);
                    exchange.getIn().setBody(job);
                })
                .process(new LinkedInUrlFinderProcessor())
//                .process(new WebsiteUrlFinderProcessor())
//                .process(new EmailExtractorProcessor())

                .process(exchange -> {
                    Job job = exchange.getIn().getBody(Job.class);
                    System.out.println(job);
                    job.setCurrentStatus(JobStatus.DONE);
                    jobServiceImpl.updateJob(job);
                });

        from("direct:exportLinkedInUrls")
                .process(new ExportLinkedinUrlAsCsvProcessor())
                .setHeader(Exchange.CONTENT_TYPE, constant("text/csv"))
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .setHeader("Content-Disposition", simple("attachment; filename=LinkedInUrls.csv"));


    }


}
