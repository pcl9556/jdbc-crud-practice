# jdbc-crud-practice

기획
1. 종합병원의 데이터 관리 프로그래을 만든다.
2. 환자,의사,진료,처방,제약코드를 관리한다.

논리
<img width="851" height="744" alt="병원 - 논리" src="https://github.com/user-attachments/assets/cdf747fc-df27-4567-a1cf-8e4285e82c23" />



물리
<img width="1094" height="715" alt="병원 - 물리" src="https://github.com/user-attachments/assets/6cec64a0-6eb1-4854-85a2-6cd6102d441f" />




[DROP TABLE `clinic`;

CREATE TABLE `clinic`
(
    `clinic_code`    INTEGER(3) NOT NULL COMMENT '진료코드',
    `doctor_code`    INTEGER(3) NOT NULL COMMENT '의사코드',
    `patient_code`    INTEGER(3) NOT NULL COMMENT '환자코드',
    `prescription_code`    VARCHAR(3) COMMENT '처방코드',
 PRIMARY KEY ( `clinic_code` )
) COMMENT = '진료';

ALTER TABLE `clinic`
 ADD CONSTRAINT `clinic_PK` PRIMARY KEY ( `clinic_code` );


DROP TABLE `doctor`;

CREATE TABLE `doctor`
(
    `clinic_day`    VARCHAR(20) NOT NULL COMMENT '진료날짜',
    `doctor_code`    INTEGER(3) NOT NULL COMMENT '의사코드',
    `doctor_name`    VARCHAR(10) NOT NULL COMMENT '의사이름',
    `specialty`    VARCHAR(10) NOT NULL COMMENT '전공과목',
    `clinic_hours`    VARCHAR(10) NOT NULL COMMENT '진료시간',
 PRIMARY KEY ( `doctor_code` )
) COMMENT = '의사';

ALTER TABLE `doctor`
 ADD CONSTRAINT `doctor_PK` PRIMARY KEY ( `doctor_code` );


DROP TABLE `patient`;

CREATE TABLE `patient`
(
    `patient_code`    VARCHAR(3) NOT NULL COMMENT '환자코드',
    `patient_name`    VARCHAR(10) NOT NULL COMMENT '환자이름',
    `patient_number`    VARCHAR(15) NOT NULL COMMENT '환자전화번호',
    `symptoms`    VARCHAR(20) NOT NULL COMMENT '내원증상',
 PRIMARY KEY ( `patient_code` )
) COMMENT = '환자';

ALTER TABLE `patient`
 ADD CONSTRAINT `patient_PK` PRIMARY KEY ( `patient_code` );


DROP TABLE `pharmaceutical`;

CREATE TABLE `pharmaceutical`
(
    `pharmaceutical_code`    INTEGER(3) NOT NULL COMMENT '제약코드',
    `prescription_code`    VARCHAR(3) NOT NULL COMMENT '처방코드',
    `drug_name`    VARCHAR(20) NOT NULL COMMENT '약품명',
    `dosage`    VARCHAR(20) NOT NULL COMMENT '복용',
 PRIMARY KEY ( `pharmaceutical_code` )
) COMMENT = '제약';

ALTER TABLE `pharmaceutical`
 ADD CONSTRAINT `pharmaceutical_PK` PRIMARY KEY ( `pharmaceutical_code` );


DROP TABLE `prescription`;

CREATE TABLE `prescription`
(
    `prescription_code`    VARCHAR(3) NOT NULL COMMENT '처방코드',
    `clinic_code`    INTEGER(3) NOT NULL COMMENT '진료코드',
    `disease_name`    VARCHAR(20) NOT NULL COMMENT '병명',
 PRIMARY KEY ( `prescription_code` )
) COMMENT = '처방전';

ALTER TABLE `prescription`
 ADD CONSTRAINT `prescription_PK` PRIMARY KEY ( `prescription_code` );Uploading 병원.sql…]()

