package org.gs.services;

import org.gs.Database;
import org.gs.props.PropertiesUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabasePopulateService {
    private static final String WORKER_CSV_KEY = "worker.csv";
    private static final String CLIENT_CSV_KEY = "client.csv";
    private static final String PROJECT_CSV_KEY = "project.csv";
    private static final String PROJECT_WORKER_CSV_KEY = "project.worker.csv";
    private static final String INSERT_WORKER_QUERY = "INSERT INTO worker(name, birthday, level, salary) VALUES (?, ?, ?, ?)";
    private static final String INSERT_CLIENT_QUERY = "INSERT INTO client(name) VALUES (?)";
    private static final String INSERT_PROJECT_QUERY = "INSERT INTO project(client_id, start_date, finish_date) VALUES (?, ?, ?)";
    private static final String INSERT_PROJECT_WORKER_QUERY = "INSERT INTO project_worker(project_id, worker_id) VALUES (?, ?)";
    Database database = Database.getInstance();

    public void populateWorkerTable() {
        String csvFilePath = PropertiesUtil.getPropertyValue(WORKER_CSV_KEY);
        try (PreparedStatement prepStatement = database.getConnection().prepareStatement(INSERT_WORKER_QUERY)) {
            try {
                List<String> csvStrings = Files.readAllLines(Path.of(csvFilePath));
                for (String string: csvStrings) {
                    String[] values = string.split(",");
                    prepStatement.setString(1, values[0]);
                    prepStatement.setString(2, values[1]);
                    prepStatement.setString(3, values[2]);
                    prepStatement.setString(4, values[3]);
                    prepStatement.addBatch();
                }
                prepStatement.executeBatch();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateClientTable() {
        String csvFilePath = PropertiesUtil.getPropertyValue(CLIENT_CSV_KEY);
        try(PreparedStatement prepStatement = database.getConnection().prepareStatement(INSERT_CLIENT_QUERY)) {
            try {
                List<String> csvStrings = Files.readAllLines(Path.of(csvFilePath));
                for (String string: csvStrings) {
                    String[] values = string.split(",");
                    prepStatement.setString(1, values[0]);
                    prepStatement.addBatch();
                }
                prepStatement.executeBatch();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateProjectTable() {
        String csvFilePath = PropertiesUtil.getPropertyValue(PROJECT_CSV_KEY);
        try(PreparedStatement prepStatement = database.getConnection().prepareStatement(INSERT_PROJECT_QUERY)) {
            try {
                List<String> csvStrings = Files.readAllLines(Path.of(csvFilePath));
                for (String string: csvStrings) {
                    String[] values = string.split(",");
                    prepStatement.setString(1, values[0]);
                    prepStatement.setString(2, values[1]);
                    prepStatement.setString(3, values[2]);
                    prepStatement.addBatch();
                }
                prepStatement.executeBatch();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateProjectWorkerTable() {
        String csvFilePath = PropertiesUtil.getPropertyValue(PROJECT_WORKER_CSV_KEY);
        try(PreparedStatement prepStatement = database.getConnection().prepareStatement(INSERT_PROJECT_WORKER_QUERY)) {
            try {
                List<String> csvStrings = Files.readAllLines(Path.of(csvFilePath));
                for (String string: csvStrings) {
                    String[] values = string.split(",");
                    prepStatement.setString(1, values[0]);
                    prepStatement.setString(2, values[1]);
                    prepStatement.addBatch();
                }
                prepStatement.executeBatch();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}