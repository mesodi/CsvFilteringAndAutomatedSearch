package es.wacoco.csvfilteringandautomatedsearch.Camel.routes;

import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.*;
import org.apache.camel.builder.RouteBuilder;

public class ProcessorRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:csvFilteringRoute")
                .process(new FilterCsvProcessor())
                .process(new CountApplicantProcessor());


        from("direct:processorManager")
                .process(new LinkedInUrlFinderProcessor())

                .process(new WebsiteUrlFinderProcessor())

                .process(new EmailExtractorProcessor());

        from("direct:exportLinkedinUrlAsCsvRoute")
                .process(new ExportLinkedinUrlAsCsvProcessor());
    }

}
