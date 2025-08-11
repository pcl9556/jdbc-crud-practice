# jdbc-crud-practice

기획
1. 종합병원의 데이터 관리 프로그래을 만든다.
2. 환자,의사,진료,처방,제약코드를 관리한다.

논리
<img width="851" height="744" alt="병원 - 논리" src="https://github.com/user-attachments/assets/cdf747fc-df27-4567-a1cf-8e4285e82c23" />



물리
<img width="1094" height="715" alt="병원 - 물리" src="https://github.com/user-attachments/assets/6cec64a0-6eb1-4854-85a2-6cd6102d441f" />


DROP TABLE IF EXISTS pharmaceutical;
DROP TABLE IF EXISTS prescription;
DROP TABLE IF EXISTS clinic;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS doctor;

/* 의사 */
CREATE TABLE doctor (
  clinicday    DATE         NOT NULL COMMENT '진료날짜',
  doctor_code  INT          NOT NULL COMMENT '의사코드',
  doctor_name  VARCHAR(10)  NOT NULL COMMENT '의사이름',
  specialty    VARCHAR(10)  NOT NULL COMMENT '전공과목',
  clinic_hours VARCHAR(10)  NOT NULL COMMENT '진료시간',
  PRIMARY KEY (doctor_code)
) COMMENT='의사';

/* 환자 */
CREATE TABLE patient (
  patient_code   INT          NOT NULL COMMENT '환자코드',
  patient_name   VARCHAR(10)  NOT NULL COMMENT '환자이름',
  patient_number VARCHAR(15)  NOT NULL COMMENT '환자전화번호',
  symptoms       VARCHAR(20)  NOT NULL COMMENT '내원증상',
  PRIMARY KEY (patient_code),
  UNIQUE (patient_number)
) COMMENT='환자';

/* 진료 (처방코드는 NULL 가능) */
CREATE TABLE clinic (
  clinic_code       INT NOT NULL COMMENT '진료코드',
  doctor_code       INT NOT NULL COMMENT '의사코드',
  patient_code      INT NOT NULL COMMENT '환자코드',
  prescription_code INT NULL COMMENT '처방코드(있을 수도 없음)',
  PRIMARY KEY (clinic_code),
  FOREIGN KEY (doctor_code)  REFERENCES doctor(doctor_code),
  FOREIGN KEY (patient_code) REFERENCES patient(patient_code)
) COMMENT='진료';

/* 처방전  */
CREATE TABLE prescription (
  prescription_code INT NOT NULL COMMENT '처방코드',
  clinic_code       INT NOT NULL COMMENT '진료코드',
  disease_name      VARCHAR(20) NOT NULL COMMENT '병명',
  PRIMARY KEY (prescription_code),
  FOREIGN KEY (clinic_code) REFERENCES clinic(clinic_code),
  UNIQUE (clinic_code)  
) COMMENT='처방전';

/* 제약(투약) */
CREATE TABLE pharmaceutical (
  pharmaceutical_code INT NOT NULL COMMENT '제약코드',
  prescription_code   INT NOT NULL COMMENT '처방코드',
  drug_name           VARCHAR(20) NOT NULL COMMENT '약품명',
  dosage              VARCHAR(50) NOT NULL COMMENT '복용법',
  PRIMARY KEY (pharmaceutical_code),
  FOREIGN KEY (prescription_code) REFERENCES prescription(prescription_code)
) COMMENT='제약';

/* ------ 데이터: 각 테이블 4행씩 ------ */

/* doctor: 4명 */
INSERT INTO doctor (clinicday, doctor_code, doctor_name, specialty, clinic_hours) VALUES
('2025-08-08', 1, '박선생', '내과',     '10시'),
('2025-08-09', 2, '이선생', '외과',     '9시'),
('2025-08-08', 3, '김선생', '신경과',   '14시'),
('2025-08-10', 4, '조선생', '산부인과', '11시');

/* patient: 4명 */
INSERT INTO patient (patient_code, patient_name, patient_number, symptoms) VALUES
(5, '김환자', '010-1234-5555', '고열'),
(3, '이환자', '010-1234-3333', '복통'),
(9, '조환자', '010-1234-9999', '몸살'),
(7, '박환자', '010-1234-7777', '두통');

/* clinic: 4건 */
INSERT INTO clinic (clinic_code, doctor_code, patient_code, prescription_code) VALUES
(10, 3, 5, 100),   -- 장염 처방 예정
(20, 2, 7, 200),   -- 감기 처방 예정
(30, 1, 3, NULL),  -- 처방 없음(현재)
(40, 4, 9, NULL);  -- 처방 없음(현재)

/* prescription: 4건 */
INSERT INTO prescription (prescription_code, clinic_code, disease_name) VALUES
(100, 10, '장염'),
(200, 20, '감기'),
(300, 30, '위염'),
(400, 40, '편두통');

/* pharmaceutical: 4건  */
INSERT INTO pharmaceutical (pharmaceutical_code, prescription_code, drug_name, dosage) VALUES
(9001, 100, '타이레놀',  '식후 30분 복용'),
(9002, 200, '페니실린',  '식후 30분 복용'),
(9003, 300, '후시딘',    '하루 3회 도포'),
(9004, 400, '리파아제',  '식후 30분 복용');

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

    /* ------- close helpers ------- */
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

