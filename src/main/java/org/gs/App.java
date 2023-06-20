package org.gs;

import org.gs.models.*;
import org.gs.services.DatabaseInitService;
import org.gs.services.DatabasePopulateService;
import org.gs.services.DatabaseQueryService;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // DB init
        Database database = Database.getInstance();
        new DatabaseInitService().initDb(database);

        // Populate DB
        DatabasePopulateService populateDatabase = new DatabasePopulateService();
        populateDatabase.populateWorkerTable();
        populateDatabase.populateClientTable();
        populateDatabase.populateProjectTable();
        populateDatabase.populateProjectWorkerTable();

        // Select queries
        List<MaxSalaryWorker> maxSalaryWorker = new DatabaseQueryService().findMaxSalaryWorker();
        for (MaxSalaryWorker el: maxSalaryWorker) {
            System.out.println(el);
        }

        List<MaxProjectsClient> maxProjectsClient = new DatabaseQueryService().findMaxProjectsClient();
        for (MaxProjectsClient el: maxProjectsClient) {
            System.out.println(el);
        }

        List<LongestProject> longestProject = new DatabaseQueryService().findLongestProject();
        for (LongestProject el: longestProject) {
            System.out.println(el);
        }

        List<YoungestEldestWorkers> youngestEldestWorkers = new DatabaseQueryService().findYoungestEldestWorkers();
        for (YoungestEldestWorkers el: youngestEldestWorkers) {
            System.out.println(el);
        }

        List<ProjectPrices> projectPrices = new DatabaseQueryService().printProjectPrices();
        for (ProjectPrices el: projectPrices) {
            System.out.println(el);
        }
    }
}