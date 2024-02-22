package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractorProcessor implements Processor {

    private static final Pattern EMAIL_PATTERN = Pattern.compile
            ("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");

    @Override
    public void process(Exchange exchange) throws Exception {
        String text = exchange.getIn().getBody(String.class);
        Matcher matcher = EMAIL_PATTERN.matcher(text);

        List<String> emailAddresses = new ArrayList<>();
        while (matcher.find()) {
            emailAddresses.add(matcher.group());
        }

        exchange.getMessage().setBody(emailAddresses);
    }
}
