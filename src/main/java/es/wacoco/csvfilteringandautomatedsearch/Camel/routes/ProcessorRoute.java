package es.wacoco.csvfilteringandautomatedsearch.Camel.routes;

import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.*;
import org.apache.camel.builder.RouteBuilder;

public class ProcessorRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:processorManager")
                .process(new FilterCsvRoute())

                .process(new CountApplicantRoute())

                .process(new LinkedInUrlFinderRoute())

                .process(new WebsiteUrlFinderRoute())

                .process(new EmailExtractorRoute());


        from("direct:exportLinkedinUrlAsCsvRoute")
                .process(new ExportLinkedinRoute());
    }
}
