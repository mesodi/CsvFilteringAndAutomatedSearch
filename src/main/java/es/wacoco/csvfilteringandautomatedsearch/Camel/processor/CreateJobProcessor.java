package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class CreateJobProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        List<Company> companies = exchange.getIn().getBody(List.class);


    }
    public static String generateUniqueID(){
        String staticPart="QRY";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Random random=new Random();
        int randomPart=100000+random.nextInt(900000);
        //Generate a random Six-digit numbers
        return staticPart+"-"+formatter+"-"+randomPart;

    }

}
