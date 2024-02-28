package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractorProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {

    }
    public void searchForEmails(String url, List<String> emails) {
        try {
            Document htmlDocument = Jsoup.connect(url).get();
            if (htmlDocument == null) {
                System.out.println("ERROR! Unable to fetch HTML document from URL: " + url);
                return;
            }
            String input = htmlDocument.toString();

            Pattern pattern = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE);

            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                emails.add(matcher.group());
            }
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }
}

