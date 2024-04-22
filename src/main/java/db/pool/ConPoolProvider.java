package db.pool;

import org.jooq.ConnectionProvider;
import org.jooq.exception.DataAccessException;

import java.sql.Connection;

public interface ConPoolProvider extends ConnectionProvider {

    Connection acquire() throws DataAccessException;
    void release(Connection connection) throws DataAccessException;

}
