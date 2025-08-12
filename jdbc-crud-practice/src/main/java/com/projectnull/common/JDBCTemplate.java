package com.projectnull.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    private static final String PROP_PATH =
            "src/main/java/com/projectnull/config/connection-info.properties";

    private JDBCTemplate() {}

    public static Connection getConnection() {
        Properties prop = new Properties();
        try (FileReader reader = new FileReader(PROP_PATH)) {
            prop.load(reader);

            String driver   = prop.getProperty("driver");
            String url      = prop.getProperty("url");
            String user     = prop.getProperty("user");
            String password = prop.getProperty("password");

            if (driver == null || url == null || user == null || password == null) {
                throw new IllegalStateException(
                        "Missing required keys (driver/url/user/password) in " + PROP_PATH
                );
            }

            Class.forName(driver); // 예: com.mysql.cj.jdbc.Driver
            // URL 파라미터는 url에, 계정은 명시적으로 넘기는게 안전
            Connection con = DriverManager.getConnection(url, user, password);
            // 필요시 수동 트랜잭션 사용:
            // con.setAutoCommit(false);
            return con;

        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to get DB connection. Check " + PROP_PATH, e);
        }
    }

    /* ------- transaction helpers (옵션) ------- */
    public static void setAutoCommit(Connection con, boolean autoCommit) {
        try {
            if (con != null) con.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commit(Connection con) {
        try {
            if (con != null && !con.getAutoCommit()) con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback(Connection con) {
        try {
            if (con != null && !con.getAutoCommit()) con.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* ------- close helpers ------- */
    public static void close(Connection con) {
        try {
            if (con != null && !con.isClosed()) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(PreparedStatement pstmt) {
        try {
            if (pstmt != null && !pstmt.isClosed()) pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rset) {
        try {
            if (rset != null && !rset.isClosed()) rset.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


