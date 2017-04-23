package ua.kiev.prog.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class TownServices {
    public Connection getDbConn() {
        if (_dbConn == null) {
            _dbConn = CheckConnection();
        }
        return _dbConn;
    }

    public void setDbConn(Connection aDbConn) {
        _dbConn = aDbConn;
    }

    public static String getDbUrl() {
        return _dbUrl;
    }

    public static void setDbUrl(String aDbUrl) {
        _dbUrl = aDbUrl;
    }

    public static String getUser() {
        return _user;
    }

    public static void setUser(String aUser) {
        _user = aUser;
    }

    public static String getDbPassword() {
        return _dbPassword;
    }

    public static void setDbPassword(String aDbPassword) {
        _dbPassword = aDbPassword;
    }

    private List<Town> _collectionOfTowns;
    private static Connection _dbConn;
    private static String _dbUrl = "jdbc:mysql://localhost:3306/ukrlit";
    private static String _user = "root";
    private static String _dbPassword = "inkheart";
    private final static Logger LOGGER = Logger.getLogger(TownServices.class.getName());

    public TownServices() {
        _collectionOfTowns = new ArrayList<>();
        _dbUrl = "jdbc:mysql://localhost:3306/UkrLit";
        _user = "root";
        _dbPassword = "inkheart";
    }

    public List<Town> getAllTowns(int limitStart, int limitMax) {
        ResultSet rs = null;
        String strsql = "Select * from Towns order by ID DESC limit " + Integer.toString(limitStart) + "," + Integer.toString(limitMax);
        Connection conn = null;
        try {
            conn = CheckConnection();
            conn.setAutoCommit(true);
            PreparedStatement prepStatement = conn.prepareStatement(strsql);
            rs = prepStatement.executeQuery();
            while (rs.next()) {
                Town town = new Town();
                Date date = new Date(System.currentTimeMillis());
                town.setCreationDate(rs.getDate("CreationDate"));
                town.setCreationUser_ID(rs.getInt("CreationUser_ID"));
                town.setID(rs.getInt("ID"));
                town.setTownName(rs.getString("townName"));
                town.setUpdatingDate(rs.getDate("UpdatingDate"));
                town.setUpdatingUser_ID(rs.getInt("UpdatingUser_ID"));
                _collectionOfTowns.add(town);
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        } finally {
            closeConnection();
        }
        return _collectionOfTowns;
    }

    public void closeConnection() {
        try {
            if (_dbConn != null) {
                _dbConn.close();
            }
        } catch (SQLException ex) {
            //handle catch
        }
    }

    public Connection CheckConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.info(e.getMessage());
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(_dbUrl, _user, _dbPassword);
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
        if (connection != null) {
            LOGGER.info("You made it, take control on your database now!");

        } else {
            LOGGER.info("Failed to make connection!");
        }
        return connection;
    }

    public boolean deleteTown(String ID) {
        boolean isSuccess = false;
        ResultSet rs = null;
        String strsql = "delete from Town where ID=?";
        PreparedStatement prepStatement = null;
        Connection conn = null;
        try {
            conn = CheckConnection();
            conn.setAutoCommit(true);
            int rtnCode = 0;
            getDbConn().setAutoCommit(false);
            prepStatement = conn.prepareStatement(strsql);
            prepStatement.setString(1, ID);
            rtnCode = prepStatement.executeUpdate();
            if (rtnCode > 0) {
                _dbConn.commit();
                isSuccess = true;
            } else {
                _dbConn.rollback();
                isSuccess = false;
            }
        } catch (SQLException ex) {
            //catch handler
        } finally {
            closeConnection();
        }
        return isSuccess;
    }

    public int countTableDataRow(String TableName) {
        int ValCount = 0;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = CheckConnection();
            conn.setAutoCommit(true);
            String strsql = "SELECT Count(*) FROM " + TableName;
            stmt = conn.createStatement();
            try (ResultSet rs = stmt.executeQuery(strsql)) {
                rs.next();
                ValCount = rs.getInt(1);
            }
        } catch (SQLException se) {
            //handle catch
        } finally {
            closeConnection();
        }
        return ValCount;
    }

    public List<Town> getCollectionOfTowns() {
        return _collectionOfTowns;
    }

    public void setCollectionOfTowns(List<Town> _collectionOfTowns) {
        this._collectionOfTowns = _collectionOfTowns;
    }
}