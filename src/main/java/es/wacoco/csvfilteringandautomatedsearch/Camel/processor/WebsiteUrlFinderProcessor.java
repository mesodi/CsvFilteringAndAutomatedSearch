package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteUrlFinderProcessor implements Processor {

    private static final Pattern WEBSITE_URL_PATTERN = Pattern.compile("https?://(www\\.)?\\S+");

    @Override
    public void process(Exchange exchange) throws Exception {
        String text = exchange.getIn().getBody(String.class);
        Matcher matcher = WEBSITE_URL_PATTERN.matcher(text);

        List<String> websiteUrls = new ArrayList<>();
        while (matcher.find()) {
            websiteUrls.add(matcher.group());
        }

        exchange.getMessage().setBody(websiteUrls);
    }
}
