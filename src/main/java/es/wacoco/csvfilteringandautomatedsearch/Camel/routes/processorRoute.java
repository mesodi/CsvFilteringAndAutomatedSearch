package es.wacoco.csvfilteringandautomatedsearch.Camel.routes;

import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.CountApplicantProcessor;
import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.ExportLinkedinUrlAsCsvProcessor;
import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.FilterCsvProcessor;
import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.LinkedInUrlFinderProcessor;
import org.apache.camel.builder.RouteBuilder;

public class processorRoute  extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("direct:processorManager")
                .process(new FilterCsvProcessor())


                .process(new CountApplicantProcessor())


                .process(new LinkedInUrlFinderProcessor())


                .process(new ExportLinkedinUrlAsCsvProcessor());

    }
}
