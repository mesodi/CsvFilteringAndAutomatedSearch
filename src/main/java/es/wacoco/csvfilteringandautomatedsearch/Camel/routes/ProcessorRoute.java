package es.wacoco.csvfilteringandautomatedsearch.Camel.routes;
import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.*;
import org.apache.camel.builder.RouteBuilder;

public class ProcessorRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // Define a list to hold chosen companies

        from("direct:processorManager")
                .process(new CountApplicantProcessor())
                .process(new FilterCsvRouteProcessor())
                .process(new LinkedInUrlFinderProcessor())
                .process(new WebsiteUrlFinderProcessor())
                .process(new EmailExtractorProcessor());

        from("direct:exportLinkedinUrlAsCsvRoute")
                .process(new ExportLinkedinUrlAsCsvProcessor());

    }
}
