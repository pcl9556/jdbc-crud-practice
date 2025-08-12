# jdbc-crud-practice

기획
1. 종합병원의 데이터 관리 프로그래을 만든다.
2. 환자,의사,진료,처방,제약코드를 관리한다.

논리
<img width="851" height="744" alt="병원 - 논리" src="https://github.com/user-attachments/assets/cdf747fc-df27-4567-a1cf-8e4285e82c23" />



물리
<img width="1094" height="715" alt="병원 - 물리" src="https://github.com/user-attachments/assets/6cec64a0-6eb1-4854-85a2-6cd6102d441f" />




잠시 여기 있어
package com.projectnull.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    private JDBCTemplate() {}

    public static Connection getConnection() {
        Properties prop = new Properties();
        try (FileReader reader = new FileReader("src/main/java/com/projectnull/config/connection-info.properties")) {
            prop.load(reader);

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);                  // 예: com.mysql.cj.jdbc.Driver
            return DriverManager.getConnection(url, prop); // prop 안에 user/password 포함
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(PreparedStatement pstmt) {
        try {
            if (pstmt != null && !pstmt.isClosed()) {
                pstmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rset) {
        try {
            if (rset != null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

