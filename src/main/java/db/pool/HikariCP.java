package db.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.jooq.exception.DataAccessException;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCP implements ConPoolProvider {

    private static final String HIKARI_CONFIG_FILE = "/hikari.properties";


    private HikariConfig config;
    private HikariDataSource dataSource;

    public HikariCP() {
        init(HIKARI_CONFIG_FILE);
    }

    public HikariCP(final String pathToConfigFile) {
        init(pathToConfigFile);
    }

    public HikariCP(HikariConfig config) {
        this.config = config;
        initDS();
    }

    private void init(final String pathToConfFile) {
        this.config = new HikariConfig(pathToConfFile);
        initDS();
    }

    private void initDS() {
        this.dataSource = new HikariDataSource(this.config);
        runMigrations();
    }


    private void runMigrations() {
        Flyway flyway =
                Flyway.configure().dataSource(this.dataSource).load();
        flyway.migrate();
    }


    @Override
    public Connection acquire() throws DataAccessException {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void release(Connection connection) throws DataAccessException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
