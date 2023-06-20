package org.gs.services;

import org.gs.Database;
import org.gs.props.PropertiesUtil;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatabaseInitService {
    private static final String INIT_DB_KEY = "init.db.sql";

    public void initDb(Database database) {
        String initDbSql = PropertiesUtil.getPropertyValue(INIT_DB_KEY);
        try {
            String sql = String.join("\n", Files.readAllLines(Path.of(initDbSql)));
            database.executeUpdate(sql);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}