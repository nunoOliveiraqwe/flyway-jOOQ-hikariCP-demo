package db.persistance;

import db.persistance.repository.example.ExampleRepository;


public interface RepositoryFactory {

    ExampleRepository getExampleRepositoryRepository();

}
