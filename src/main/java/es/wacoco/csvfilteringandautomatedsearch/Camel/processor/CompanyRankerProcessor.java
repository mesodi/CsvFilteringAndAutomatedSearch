package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class  CompanyRankerProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // Assuming the body contains a List<Company>
        List<Company> companies = exchange.getIn().getBody(List.class);
        List<String> rankedCompanies = rankCompanies(companies);

        // Assuming getMessage() is the correct method to use instead of getOut()
        exchange.getMessage().setBody(rankedCompanies);
    }

    public List<String> rankCompanies(List<Company> companies) {
        Map<String, Integer> companyFrequencyMap = new HashMap<>();

        for (Company company : companies) {
            String companyName = company.getApplicant(); // Using the getter method from Lombok
            companyFrequencyMap.put(companyName, companyFrequencyMap.getOrDefault(companyName, 0) + 1);
        }

        return companyFrequencyMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
