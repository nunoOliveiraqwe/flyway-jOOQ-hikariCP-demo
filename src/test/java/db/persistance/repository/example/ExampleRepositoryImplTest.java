package db.persistance.repository.example;

import com.zaxxer.hikari.HikariConfig;
import db.model.tables.pojos.ExampleEntity;
import db.persistance.RepositoryFactory;
import db.persistance.RepositoryFactoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleRepositoryImplTest {

    private static final String DB_NAME = "test.db";
    protected static RepositoryFactory factory;

    @BeforeAll
    protected static void initializeTestFactory(@TempDir File tempDir) {
        File dbFile = new File(tempDir, DB_NAME);
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:" + dbFile.getAbsolutePath());
        config.setPoolName("sqlLitePool");
        Properties properties = new Properties();
        properties.setProperty("cachePrepStmts", String.valueOf(true));
        config.setDataSourceProperties(properties);
        factory = new RepositoryFactoryImpl(config);
    }


    @Test
    public void findAll() {
        ExampleRepository exampleRepositoryRepository = factory.getExampleRepositoryRepository();
        List<ExampleEntity> exampleEntities = exampleRepositoryRepository.findAll();
        assertFalse(exampleEntities.isEmpty());
    }

    @Test
    public void add() {
        ExampleRepository exampleRepository = factory.getExampleRepositoryRepository();
        ExampleEntity exampleEntity = new ExampleEntity();
        exampleEntity.setName("test");
        exampleEntity.setDescription("description");
        exampleEntity.setId(UUID.randomUUID().toString());
        exampleRepository.add(exampleEntity);
        Optional<ExampleEntity> returnedExampleEntity = exampleRepository.findById(exampleEntity.getId());
        assertTrue(returnedExampleEntity.isPresent());
        ExampleEntity entity = returnedExampleEntity.get();
        assertEquals(entity.getId(),exampleEntity.getId());
        assertEquals(entity.getName(),exampleEntity.getName());
        assertEquals(entity.getDescription(),exampleEntity.getDescription());
    }

    @Test
    public void update() {
        ExampleRepository exampleRepository = factory.getExampleRepositoryRepository();
        ExampleEntity exampleEntity = new ExampleEntity();
        exampleEntity.setName("test");
        exampleEntity.setDescription("description");
        exampleEntity.setId(UUID.randomUUID().toString());
        exampleRepository.add(exampleEntity);
        Optional<ExampleEntity> returnedExampleEntity = exampleRepository.findById(exampleEntity.getId());
        assertTrue(returnedExampleEntity.isPresent());
        ExampleEntity entity = returnedExampleEntity.get();
        assertEquals(entity.getId(),exampleEntity.getId());
        assertEquals(entity.getName(),exampleEntity.getName());
        assertEquals(entity.getDescription(),exampleEntity.getDescription());
        exampleEntity.setName("test2");
        exampleRepository.update(exampleEntity);
        returnedExampleEntity = exampleRepository.findById(exampleEntity.getId());
        assertTrue(returnedExampleEntity.isPresent());
        entity = returnedExampleEntity.get();
        assertEquals(entity.getId(),exampleEntity.getId());
        assertEquals(entity.getName(),exampleEntity.getName());
        assertEquals(entity.getDescription(),exampleEntity.getDescription());
    }

    @Test
    void findById() {
        ExampleRepository exampleRepository = factory.getExampleRepositoryRepository();
        ExampleEntity exampleEntity = new ExampleEntity();
        exampleEntity.setName("test");
        exampleEntity.setDescription("description");
        exampleEntity.setId(UUID.randomUUID().toString());
        exampleRepository.add(exampleEntity);
        Optional<ExampleEntity> returnedExampleEntity = exampleRepository.findById(exampleEntity.getId());
        assertTrue(returnedExampleEntity.isPresent());
    }

    @Test
    public void delete() {
        ExampleRepository exampleRepository = factory.getExampleRepositoryRepository();
        ExampleEntity exampleEntity = new ExampleEntity();
        exampleEntity.setName("test");
        exampleEntity.setDescription("description");
        exampleEntity.setId(UUID.randomUUID().toString());
        exampleRepository.add(exampleEntity);
        Optional<ExampleEntity> returnedExampleEntity = exampleRepository.findById(exampleEntity.getId());
        assertTrue(returnedExampleEntity.isPresent());
        exampleRepository.delete(exampleEntity.getId());
        returnedExampleEntity = exampleRepository.findById(exampleEntity.getId());
        assertFalse(returnedExampleEntity.isPresent());
    }

    @Test
    public void size() {
        ExampleRepository exampleRepository = factory.getExampleRepositoryRepository();
        long size = exampleRepository.size();
        ExampleEntity exampleEntity = new ExampleEntity();
        exampleEntity.setName("test");
        exampleEntity.setDescription("description");
        exampleEntity.setId(UUID.randomUUID().toString());
        exampleRepository.add(exampleEntity);
        assertEquals(size+1,exampleRepository.size());
    }
}