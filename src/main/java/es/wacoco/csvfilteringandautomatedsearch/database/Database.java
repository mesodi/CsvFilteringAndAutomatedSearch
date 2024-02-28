package es.wacoco.csvfilteringandautomatedsearch.database;

import es.wacoco.csvfilteringandautomatedsearch.model.Company;
import es.wacoco.csvfilteringandautomatedsearch.model.InventorUrl;
import es.wacoco.csvfilteringandautomatedsearch.model.Job;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Getter
@Setter

public class Database {
    public static final List<Company> companies = new ArrayList<>();


    public ArrayList<Job> getJobDB() {
        return jobDB;
    }

public static final List<Company> selectedCompanies = new ArrayList<>();

    private static final List<Company> processedCompanies = new ArrayList<>();
    private static final List<InventorUrl> inventorUrls = new ArrayList<>();

    ArrayList<Job> jobDB = new ArrayList<>();

    public void createJob(Job job) {
        jobDB.add(job);
    }
    public static List<Company> getProcessedCompanies() {
        return new ArrayList<>(processedCompanies);
    }
    public static void addSelectedCompanies(List<Company> companies) {
        selectedCompanies.clear();
        selectedCompanies.addAll(companies);
    }
    public static List<Company> getSelectedCompanies() {
        return new ArrayList<>(selectedCompanies);
    }
    public static void addProcessedCompanies(List<Company> companies) {
        processedCompanies.clear();
        processedCompanies.addAll(companies);}

    public static void addInventorUrl(InventorUrl inventorUrl) {
        inventorUrls.clear();
        inventorUrls.add(inventorUrl);
    }
    public static List<InventorUrl> getInventorUrls() {
        return new ArrayList<>(inventorUrls);
    }

}