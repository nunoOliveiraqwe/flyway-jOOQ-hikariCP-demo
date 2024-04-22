package db.persistance.repository.example;


import db.model.tables.daos.ExampleEntityDao;
import db.model.tables.pojos.ExampleEntity;
import db.model.tables.records.ExampleEntityRecord;
import org.jooq.Configuration;
import org.jooq.RecordMapper;



import java.util.List;
import java.util.Optional;

public class ExampleRepositoryImpl implements ExampleRepository {

    private final ExampleEntityDao dao;
    private final ExampleEntityMapper mapper;

    public ExampleRepositoryImpl(Configuration configuration) {
        this.dao = new ExampleEntityDao(configuration);
        this.mapper = new ExampleEntityMapper();
    }

    @Override
    public List<ExampleEntity> findAll() {
        return this.dao.findAll();
    }

    @Override
    public void add(ExampleEntity entity) {
        this.dao.insert(entity);
    }

    @Override
    public void update(ExampleEntity entity) {
        this.dao.update(entity);
    }

    @Override
    public Optional<ExampleEntity> findById(String id) {
        ExampleEntity byId = this.dao.findById(id);
        return Optional.ofNullable(byId);
    }

    @Override
    public void delete(String id) {
       this.dao.deleteById(id);
    }

    @Override
    public long size() {
        return this.dao.count();
    }


    private static class ExampleEntityMapper implements RecordMapper<ExampleEntityRecord, db.model.tables.pojos.ExampleEntity> {

        @Override
        public db.model.tables.pojos.ExampleEntity map(ExampleEntityRecord record) {
            return new db.model.tables.pojos.ExampleEntity(record.getId(), record.getName(), record.getDescription(),
                    record.getCreatedAt(),record.getUpdatedAt(),record.getDeletedAt());
        }
    }

}
