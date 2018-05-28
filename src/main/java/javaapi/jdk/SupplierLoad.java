package javaapi.jdk;

import java.sql.Connection;
import java.sql.SQLException;

public class SupplierLoad {

    public static void main(String[] args) {
        YpeanngDataSource dataSource = YpeanngDataSource.instance();
        try {
            Connection connection = dataSource.getConnection();
            connection.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
