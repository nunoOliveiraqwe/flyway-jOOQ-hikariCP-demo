package db.persistance.repository;


import java.util.List;
import java.util.Optional;

public interface Repository<T, K> {

    /**
     * Gets all the entities available
     *
     * @return a collection of non-null entities
     */
    List<T> findAll();

    /**
     * Creates a new an entity in the persistence. if the entity already
     * exists it will throw an exception.
     *
     * @param entity The entity to add
     * @return true if the entity was added, false otherwise
     * @throws UnsupportedOperationException if the add operation makes no
     *                                       sense for the concrete repository
     */
    void add(T entity);


    /**
     * Updates an existing entity
     *
     * @param entity The entity to update
     * @return true if the entity was update, false otherwise
     * @throws UnsupportedOperationException if the add operation makes no
     *                                       sense for the concrete repository
     */
    void update(T entity);

    /**
     * Gets the entity with the specified id
     *
     * @param id - The identifier of the entity
     * @return An optional of the entity
     */
    Optional<T> findById(K id);

    /**
     * Deletes the entity from the persistence
     *
     * @param entity - The entity to delete
     * @return true if the entity was deleted from the persistence, false otherwise
     * @throws UnsupportedOperationException if the delete operation makes no
     *                                       sense for the concrete repository
     */
    void delete(K entity);

    /**
     * Returns the number of entities in the repository.
     *
     * @return - the number of entities in the repository
     */
    long size();
}
