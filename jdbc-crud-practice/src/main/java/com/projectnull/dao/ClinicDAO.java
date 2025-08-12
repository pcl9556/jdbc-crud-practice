package com.projectnull.dao;

import com.projectnull.dto.ClinicDTO;
import com.projectnull.dto.ClinicDetailDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.projectnull.common.JDBCTemplate.close;

public class ClinicDAO {

    private final Properties prop = new Properties();

    public ClinicDAO() {
        try {

            prop.loadFromXML(new FileInputStream("src/main/java/com/projectnull/mapper/hospital-query.xml"));

        } catch (IOException e) {
            throw new RuntimeException("쿼리 XML 로드 실패", e);
        }
    }

    /* ===================== Create ===================== */
    public int insertClinic(Connection con, ClinicDTO c) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("insertClinic");
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, c.getClinicCode());
            pstmt.setInt(2, c.getDoctorCode());
            pstmt.setInt(3, c.getPatientCode());
            if (c.getPrescriptionCode() == null || c.getPrescriptionCode().isEmpty()) {
                pstmt.setNull(4, Types.VARCHAR);
            } else {
                pstmt.setString(4, c.getPrescriptionCode());
            }
            return pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
    }

    /* ===================== Read (기본 목록) ===================== */
    public List<ClinicDTO> selectAllClinics(Connection con) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ClinicDTO> list = new ArrayList<ClinicDTO>();
        String sql = prop.getProperty("selectAllClinics");
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new ClinicDTO(
                        rs.getInt("clinic_code"),
                        rs.getInt("doctor_code"),
                        rs.getInt("patient_code"),
                        rs.getString("prescription_code")
                ));
            }
            return list;
        } finally {
            close(rs);
            close(pstmt);
        }
    }

    /* ===================== Read (JOIN 전체) ===================== */
    public List<ClinicDetailDTO> selectAllClinicsDetail(Connection con) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ClinicDetailDTO> list = new ArrayList<ClinicDetailDTO>();
        String sql = prop.getProperty("selectAllClinicsDetail");
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ClinicDetailDTO d = new ClinicDetailDTO();
                d.setClinicCode(rs.getInt("clinic_code"));
                d.setDoctorCode(rs.getInt("doctor_code"));
                d.setDoctorName(rs.getString("doctor_name"));
                d.setSpecialty(rs.getString("specialty"));
                d.setClinicDay(rs.getString("clinic_day"));
                d.setClinicHours(rs.getString("clinic_hours"));

                d.setPatientCode(rs.getInt("patient_code"));
                d.setPatientName(rs.getString("patient_name"));
                d.setPatientNumber(rs.getString("patient_number"));
                d.setSymptoms(rs.getString("symptoms"));

                d.setPrescriptionCode(rs.getString("prescription_code"));
                d.setDiseaseName(rs.getString("disease_name"));

                Object ph = rs.getObject("pharmaceutical_code");
                d.setPharmaceuticalCode(ph == null ? null : ((Number) ph).intValue());
                d.setDrugName(rs.getString("drug_name"));
                d.setDosage(rs.getString("dosage"));

                list.add(d);
            }
            return list;
        } finally {
            close(rs);
            close(pstmt);
        }
    }

    /* ===================== Read (JOIN 단건) ===================== */
    public ClinicDetailDTO selectClinicDetailByCode(Connection con, int clinicCode) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = prop.getProperty("selectClinicDetailByCode");
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, clinicCode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ClinicDetailDTO d = new ClinicDetailDTO();
                d.setClinicCode(rs.getInt("clinic_code"));
                d.setDoctorCode(rs.getInt("doctor_code"));
                d.setDoctorName(rs.getString("doctor_name"));
                d.setSpecialty(rs.getString("specialty"));
                d.setClinicDay(rs.getString("clinic_day"));
                d.setClinicHours(rs.getString("clinic_hours"));

                d.setPatientCode(rs.getInt("patient_code"));
                d.setPatientName(rs.getString("patient_name"));
                d.setPatientNumber(rs.getString("patient_number"));
                d.setSymptoms(rs.getString("symptoms"));

                d.setPrescriptionCode(rs.getString("prescription_code"));
                d.setDiseaseName(rs.getString("disease_name"));

                Object ph = rs.getObject("pharmaceutical_code");
                d.setPharmaceuticalCode(ph == null ? null : ((Number) ph).intValue());
                d.setDrugName(rs.getString("drug_name"));
                d.setDosage(rs.getString("dosage"));

                return d;
            }
            return null;
        } finally {
            close(rs);
            close(pstmt);
        }
    }
    /* ===================== Update ===================== */
    public int updateClinic(Connection con, ClinicDTO c) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateClinic");
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, c.getDoctorCode());
            pstmt.setInt(2, c.getPatientCode());
            if (c.getPrescriptionCode() == null || c.getPrescriptionCode().isEmpty()) {
                pstmt.setNull(3, Types.VARCHAR);
            } else {
                pstmt.setString(3, c.getPrescriptionCode());
            }
            pstmt.setInt(4, c.getClinicCode());
            return pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
    }

    /* ===================== Delete ===================== */
    public int deleteClinic(Connection con, int clinicCode) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("deleteClinic");
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, clinicCode);
            return pstmt.executeUpdate();
        } finally {
            close(pstmt);
        }
    }

    public int deleteClinicAllInOne(Connection con, int clinicCode) throws SQLException {
        String sql = prop.getProperty("deleteClinicAllInOne");
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, clinicCode);
            int affected = ps.executeUpdate(); // 여러 테이블 합산된 삭제 건수
            return affected > 0 ? 1 : 0;       // 클리닉이 없으면 0
        } finally {
            close(ps);
        }
    }

}
