package dbUtils;

import fileUtils.FileUtil;
import logger.Logger;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnector {
    private Logger logger = new Logger(DBConnector.class);
    private static String serverUrl;
    private static String username;
    private static String password;
    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;
    private static Properties info = new Properties();

    private DBConnector(String server, String user, String passwd) {
        serverUrl = server;
        username = user;
        password = passwd;
    }
    private DBConnector(String server) {
        serverUrl = server;
    }

    public static DBConnector create(String server, String user, String passwd) {
        info.put("user", user);
        info.put("password", passwd);
        return new DBConnector(server, user, passwd);
    }

    public static DBConnector create(String server, String user, String passwd, String tmZn) {
        server += "?user=" + user + "&password=" + passwd + "&serverTimezone=" + tmZn;
        return new DBConnector(server);
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(serverUrl, info);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public String getQuery(String sqlFile) {
        try {
            FileUtil file = new FileUtil(sqlFile);
            String sql = file.read();
            return sql;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public void setAutoCommit(Boolean isSet) {
        try {
            connection.setAutoCommit(isSet);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public PreparedStatement prepareStatement(String query) {
        try {
            ps = connection.prepareStatement(query);
            return ps;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public void setString(int id, String str) {
        try {
            ps.setString(id, str);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void setInt(int id, int i) {
        try {
            ps.setInt(id, i);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void setBinaryStream(int id, FileInputStream fis, int length) {
        try {
            ps.setBinaryStream(id, fis, length);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void execute() {
        try {
            ps.execute();
        } catch (SQLException e) {
            logger.error(e);
        }
    }


    public ResultSet executeQueryFile(String sqlFile) {
        try {
            FileUtil file = new FileUtil(sqlFile);
            String sql = file.read();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }


    public int executeUpdate(String sql) {
        try {
            prepareStatement(sql);
            return ps.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }
    public int executeUpdate() {
        try {
            return ps.executeUpdate();
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    public ResultSet executeQuery(String sql){
        try {
            prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public ResultSet executeQuery(){
        try {
            return ps.executeQuery();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public void closePrepStatement() {
        try {
            ps.close();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

}
