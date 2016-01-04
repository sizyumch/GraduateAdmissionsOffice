package com.epam.gao.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {
    Connection getConnection() throws SQLException;
}
