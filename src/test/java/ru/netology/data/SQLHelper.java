package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static final String url = System.getProperty("db.url");
    private static final String user = ("app");
    private static final String pass = ("pass");
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {}

    @SneakyThrows
    private static Connection getConnection() {
        return DriverManager.getConnection(url, user, pass);
    }

    @SneakyThrows
    public static String getCardPayment() {
        var connection = getConnection();
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(connection, codeSQL, new ScalarHandler<>());
    }

       @SneakyThrows
       public static void cleanBase() {
           var connection = getConnection();
           runner.execute(connection, "DELETE FROM credit_request_entity");
           runner.execute(connection, "DELETE FROM order_entity");
           runner.execute(connection, "DELETE FROM payment_entity");
       }
}

