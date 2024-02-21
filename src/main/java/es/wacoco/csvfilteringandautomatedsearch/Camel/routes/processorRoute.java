package es.wacoco.csvfilteringandautomatedsearch.Camel.routes;

import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.*;
import org.apache.camel.builder.RouteBuilder;

public class processorRoute  extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:processorManager")
                .process(new FilterCsvProcessor())

                .process(new CountApplicantProcessor())

                .process(new LinkedInUrlFinderProcessor())

                .process(new WebsiteUrlFinderProcessor())

                .process(new EmailExtractorProcessor());


        from("direct:exportLinkedinUrlAsCsvRoute")
                .process(new ExportLinkedinUrlAsCsvProcessor());
    }
}
