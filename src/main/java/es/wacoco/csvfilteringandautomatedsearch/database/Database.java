package es.wacoco.csvfilteringandautomatedsearch.database;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.InventorUrl;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class Database {
    public static final List<Company> companies = new ArrayList<>();
    private static final Map<String, List<InventorUrl>> inventorUrlsByJob = new ConcurrentHashMap<>();


    public static void addInventorUrl(String jobId, InventorUrl inventorUrl) {
        inventorUrlsByJob.computeIfAbsent(jobId, k -> new CopyOnWriteArrayList<>()).add(inventorUrl);
    }


    public static List<InventorUrl> getInventorUrlsForJob(String jobId) {
        return inventorUrlsByJob.getOrDefault(jobId, new CopyOnWriteArrayList<>());
    }

    public static List<InventorUrl> getAllInventorUrlsWithJobId() {
        return inventorUrlsByJob.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(inventorUrl -> new InventorUrl(inventorUrl.getInventor(), inventorUrl.getLinkedInUrl(), entry.getKey())))
                .collect(Collectors.toList());
    }
}