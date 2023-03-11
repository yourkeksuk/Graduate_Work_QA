package ru.netology.java.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.java.entities.CreditRequestEntity;
import ru.netology.java.entities.OrderEntity;
import ru.netology.java.entities.PaymentEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtils {

    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.login");
    private static String password = System.getProperty("db.pass");
    private static Connection conn;

    public static Connection getNewConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.print("Connection failed");
        }
        return conn;
    }

    public static void cleanDB() {
        SQLUtils.getNewConnection();
        val cleanCreditRequest = "DELETE FROM credit_request_entity";
        val cleanPayment = "DELETE FROM payment_entity";
        val cleanOrder = "DELETE FROM order_entity";
        val runner = new QueryRunner();
        try (val conn = getNewConnection()) {
            runner.update(conn, cleanCreditRequest);
            runner.update(conn, cleanPayment);
            runner.update(conn, cleanOrder);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getPaymentStatus() {
        val selectStatus = "SELECT * FROM payment_entity";
        val runner = new QueryRunner();
        try (val conn = getNewConnection()) {
            val cardStatus = runner.query(conn, selectStatus, new BeanHandler<>(PaymentEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getPaymentTransactionId() {
        val selectTransactionId = "SELECT * FROM payment_entity";
        val runner = new QueryRunner();
        try (val conn = getNewConnection()) {
            val transactionId = runner.query(conn, selectTransactionId, new BeanHandler<>(PaymentEntity.class));
            return transactionId.getTransaction_id();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getCreditRequestStatus() {
        val selectStatus = "SELECT * FROM credit_request_entity";
        val runner = new QueryRunner();
        try (val conn = getNewConnection()) {
            val cardStatus = runner.query(conn, selectStatus, new BeanHandler<>(CreditRequestEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getCreditRequestBankId() {
        val selectBankId = "SELECT * FROM credit_request_entity";
        val runner = new QueryRunner();
        try (val conn = getNewConnection()) {
            val bankId = runner.query(conn, selectBankId, new BeanHandler<>(CreditRequestEntity.class));
            return bankId.getBank_id();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getOrderPaymentId() {
        val selectPaymentId = "SELECT * FROM order_entity";
        val runner = new QueryRunner();
        try (val conn = getNewConnection()) {
            val paymentId = runner.query(conn, selectPaymentId, new BeanHandler<>(OrderEntity.class));
            return paymentId.getPayment_id();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}