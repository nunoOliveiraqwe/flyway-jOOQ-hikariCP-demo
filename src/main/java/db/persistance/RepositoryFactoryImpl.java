package db.persistance;

import com.zaxxer.hikari.HikariConfig;
import db.persistance.repository.Repository;
import db.persistance.repository.example.ExampleRepository;
import db.persistance.repository.example.ExampleRepositoryImpl;
import db.pool.HikariCP;
import org.jooq.Configuration;
import org.jooq.ConnectionProvider;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.conf.SettingsTools;
import org.jooq.conf.StatementType;
import org.jooq.impl.DefaultConfiguration;

import java.util.EnumMap;

public class RepositoryFactoryImpl implements RepositoryFactory {


    private static final String HIKARI_CP_CONF_FILE_DEFINE_VARIABLE = "hikariConf";

    private final ConnectionProvider conPool;
    private static final SQLDialect dialect = SQLDialect.SQLITE;
    private static final Settings settings = SettingsTools.defaultSettings();

    static {
        settings.setStatementType(StatementType.STATIC_STATEMENT);
    }

    private static final Configuration configuration = new DefaultConfiguration();


    public RepositoryFactoryImpl() {
        String confFilePath = System.getProperty(HIKARI_CP_CONF_FILE_DEFINE_VARIABLE);
        conPool = confFilePath == null || confFilePath.isEmpty() ? new HikariCP() : new HikariCP(confFilePath);
        configuration.set(conPool);
        configuration.set(dialect);
        configuration.set(settings);
    }


    public RepositoryFactoryImpl(HikariConfig hikariConfig) {
        conPool = new HikariCP(hikariConfig);
        configuration.set(conPool);
        configuration.set(dialect);
        configuration.set(settings);
    }




    private final EnumMap<CacheKey, Repository<?, ?>> REPOSITORY_CACHES = new EnumMap<>(CacheKey.class);


    @Override
    public ExampleRepository getExampleRepositoryRepository() {
        return (ExampleRepository) REPOSITORY_CACHES.computeIfAbsent(CacheKey.EXAMPLE_ENTITY, cacheKey -> new ExampleRepositoryImpl(configuration));
    }


    enum CacheKey {
        EXAMPLE_ENTITY,
    }

}
