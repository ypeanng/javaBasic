package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlApp {

    public List<User> getUser() {
        // 数据库连接基本操作 这样操作数据库 是很麻烦的。所以需要Mybatis
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception ex) {

        }
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("");
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from biz_users");
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String age = resultSet.getString(3);
                user.setId(id);
                user.setAge(age);
                user.setName(name);
                userList.add(user);
            }
            return userList;
        }catch (Exception ex) {

        }finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                }catch (Exception ex){
                }
            }
        }
        return null;
    }

}
