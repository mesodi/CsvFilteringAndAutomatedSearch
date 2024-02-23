package es.wacoco.csvfilteringandautomatedsearch.Camel.processor;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UniqueIDGenerator {
    public static String generateUniqueID(){
        String staticPart="QRY";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Random random=new Random();
        int randomPart=100000+random.nextInt(900000);
        //Generate a random Six-digit numbers
        return staticPart+"-"+formatter+"-"+randomPart;

    }
}
