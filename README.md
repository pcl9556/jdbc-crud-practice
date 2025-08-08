# jdbc-crud-practice

기획

논리
<img width="851" height="744" alt="병원 - 논리" src="https://github.com/user-attachments/assets/cdf747fc-df27-4567-a1cf-8e4285e82c23" />



물리
<img width="1094" height="715" alt="병원 - 물리" src="https://github.com/user-attachments/assets/6cec64a0-6eb1-4854-85a2-6cd6102d441f" />




DROP TABLE IF EXISTS `의사`;

-- 테이블 생성
CREATE TABLE IF NOT EXISTS `의사`
(
    `clinicday`   VARCHAR(20) NOT NULL COMMENT '진료날짜',
    `doctor_code`    integer(3) NOT NULL COMMENT '의사코드',
    `doctor_name`    VARCHAR(10) NOT NULL COMMENT '의사이름',
    `specialty`    VARCHAR(10) NOT NULL COMMENT '전공과목',
    `clinichours`   VARCHAR(10) NOT NULL COMMENT '진료시간',
 PRIMARY KEY ( `doctor_code` )
) COMMENT = '의사';

INSERT INTO 의사 (clinicday,doctor_code,doctor_name,specialty,clinichours) VALUES 
('2025-08-08',3,'김선생','신경과','14시'),
('2025-08-09',2,'이선생','외과','9시'),
('2025-08-08',1,'박선생','내과','10시'),
('2025-08-10',4,'조선생','산부인과','11시');


DROP TABLE IF EXISTS `제약`;

CREATE TABLE IF NOT EXISTS`제약`
(
    `pharmaceutical_code`   integer(10) NOT NULL COMMENT '제약코드',
    `prescription_code`    integer(3) NOT NULL COMMENT '처방코드',
    `dugname`    VARCHAR(20) NOT NULL COMMENT '약품명',
    `dosage`    VARCHAR(20) NOT NULL COMMENT '복용',
 PRIMARY KEY ( `pharmaceutical_code` )
) COMMENT = '제약';

INSERT INTO 제약 (pharmaceutical_code,prescription_code,dugname,dosage) VALUES 
(111,100,'타이레놀','식후 30분 후 복용'),
(222,101,'페니실린','식후 30분 후 복용'),
(333,103,'후시딘','하루 3번 바르세요'),
(444,104,'리파아제','식후 30분 후 복용');


DROP TABLE IF EXISTS `진료`;

CREATE TABLE IF NOT EXISTS`진료`
(
    `clinic_code`    integer(3) NOT NULL COMMENT '진료코드',
    `doctor_code`    integer(3) NOT NULL COMMENT '의사코드',
    `patient_code`    integer(3) NOT NULL COMMENT '환자코드',
    `prescription_code`    integer(3) COMMENT '처방코드',
 PRIMARY KEY ( `clinic_code` )
) COMMENT = '진료';

INSERT INTO 진료 (clinic_code,doctor_code,patient_code,prescription_code) VALUES 
(10,3,05,104),
(50,5,03,103),
(70,1,02,102),
(20,2,07,100);


DROP TABLE IF EXISTS `처방전`;

CREATE TABLE IF NOT EXISTS`처방전`
(
    `prescription_code`    VARCHAR(3) NOT NULL COMMENT '처방코드',
    `clinic_code`    VARCHAR(3) NOT NULL COMMENT '진료코드',
    `diseasename`    VARCHAR(20) NOT NULL COMMENT '병명',
 PRIMARY KEY ( `prescription_code` )
) COMMENT = '처방전';

INSERT INTO 처방전 (prescription_code,clinic_code,diseasename) VALUES 
(103,10,'장염'),
(101,20,'감기'),
(106,30,'위염'),
(102,70,'맹장');


DROP TABLE IF EXISTS `환자`;

CREATE TABLE IF NOT EXISTS`환자`
(
    `patient_code`    VARCHAR(3) NOT NULL COMMENT '환자코드',
    `patient_name`    VARCHAR(10) NOT NULL COMMENT '환자이름',
    `patient_number`    VARCHAR(15) NOT NULL COMMENT '환자전화번호',
    `symptoms`    VARCHAR(20) NOT NULL COMMENT '내원증상',
 PRIMARY KEY ( `patient_code` )
) COMMENT = '환자';
INSERT INTO 환자 (patient_code,patient_name,patient_number,symptoms) VALUES 
(05,'김환자','010-1234-5555','고열'),
(03,'이환자','010-1234-3333','배아픔'),
(09,'조환자','010-1234-9999','몸살'),
(07,'박환자','010-1234-7777','두통');
