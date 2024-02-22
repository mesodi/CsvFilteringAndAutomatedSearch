package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkedInUrlFinderProcessor implements Processor {

    private static final Pattern LINKEDIN_URL_PATTERN = Pattern.compile("https?://(www\\.)?linkedin\\.com/\\S+");

    @Override
    public void process(Exchange exchange) throws Exception {
        String text = exchange.getIn().getBody(String.class);
        Matcher matcher = LINKEDIN_URL_PATTERN.matcher(text);

        List<String> linkedinUrls = new ArrayList<>();
        while (matcher.find()) {
            linkedinUrls.add(matcher.group());
        }

        exchange.getMessage().setBody(linkedinUrls);
    }
}
