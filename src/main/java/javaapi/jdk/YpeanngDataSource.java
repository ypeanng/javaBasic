package javaapi.jdk;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YpeanngDataSource implements DataSource {
//    private static Log log = LogFactory.getLog(SimpleDateSource.class);
    private static final String dirverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/testdb";
    private static final String user = "root";
    private static final String pswd = "leizhimin";

    private static LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>());
    private static YpeanngDataSource instance = new YpeanngDataSource();

    static {
        try {
            Class.forName(dirverClassName);
        } catch (ClassNotFoundException e) {
//            ("找不到驱动类！", e);
        }
    }

    private YpeanngDataSource(){}
    /**
     * 获取数据源单例
     *
     * @return 数据源单例
     */
    public static YpeanngDataSource instance() {
        if (instance == null) instance = new YpeanngDataSource();
        return instance;
    }
    /**
     * 获取一个数据库连接
     *
     * @return 一个数据库连接
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        synchronized (pool) {
            if (pool.size() > 0) return pool.removeFirst();
            else return makeConnection();
        }
    }
    /**
     * 连接归池
     *
     * @param conn
     */
    public static void freeConnection(Connection conn) {
        pool.addLast(conn);
    }

    private Connection makeConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pswd);
    }
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
