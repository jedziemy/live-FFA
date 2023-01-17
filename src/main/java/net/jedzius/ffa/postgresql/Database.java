package net.jedzius.ffa.postgresql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private final HikariDataSource dataSource;
    private final Connection connection;

    public Database() throws SQLException {
        this.dataSource = new HikariDataSource(getConfig());
        this.connection = dataSource.getConnection();
    }

    private HikariConfig getConfig() {
        HikariConfig config = new HikariConfig();

        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("portNumber", "5432");
        config.addDataSourceProperty("databaseName", "live-ffa");
        config.addDataSourceProperty("user", "postgres");
        config.addDataSourceProperty("password", "root");

        return config;
    }

    public void connect() {
        // TODO
        try {
            this.connection.prepareStatement(
                            new StringBuilder()
                                    .append("CREATE TABLE IF NOT EXISTS users")
                                    .append("(uuid TEXT NOT NULL PRIMARY KEY, ")
                                    .append("name TEXT NOT NULL, ")
                                    .append("points INTEGER, ")
                                    .append("prestige INTEGER, ")
                                    .append("kills INTEGER, ")
                                    .append("deaths INTEGER, ")
                                    .append("ks INTEGER)")
                                    .toString())
                    .executeUpdate();

            System.out.println("Connection with database has been created!");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void disconnect() throws SQLException {
        if(this.dataSource.isClosed()) return;
        this.dataSource.close();
    }

    public Connection getExistedConnection() throws SQLException {
        return this.connection;
    }
}
