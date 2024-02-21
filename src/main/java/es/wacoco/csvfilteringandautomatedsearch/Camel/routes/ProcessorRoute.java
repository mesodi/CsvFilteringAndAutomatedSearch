package es.wacoco.csvfilteringandautomatedsearch.Camel.routes;
import es.wacoco.csvfilteringandautomatedsearch.Camel.processor.*;
import es.wacoco.csvfilteringandautomatedsearch.model.ChosenCompanies;
import org.apache.camel.builder.RouteBuilder;

public class ProcessorRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // Define a list to hold chosen companies
        ChosenCompanies chosenCompanies = new ChosenCompanies();

        from("direct:processorManager")
                .process(new CountApplicantProcessor())
                .process(new FilterCsvRouteProcessor())
                .process(new LinkedInUrlFinderProcessor())
                .process(new WebsiteUrlFinderProcessor())
                .process(new EmailExtractorProcessor())
                .process(exchange -> {
                    // Add chosen companies to the list
                    String chosenCompany = exchange.getIn().getHeader("chosenCompany", String.class);
                    chosenCompanies.addCompany(chosenCompany);
                });

        from("direct:exportLinkedinUrlAsCsvRoute")
                .process(new ExportLinkedinUrlAsCsvProcessor());

        // Make the chosen companies list available for retrieval if needed
        getContext().getRegistry().bind("chosenCompanies", chosenCompanies);
    }
}
