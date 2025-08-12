package com.projectnull.dto;

public class ClinicDTO {
    private int clinicCode;     // PK
    private int doctorCode;     // FK -> doctor
    private int patientCode;    // FK -> patient
    private String prescriptionCode; // FK -> prescription (nullable)

    public ClinicDTO() {}

    // 처방전 없이 진료만 등록할 때 편의 생성자
    public ClinicDTO(int clinicCode, int doctorCode, int patientCode) {
        this(clinicCode, doctorCode, patientCode, null);
    }

    public ClinicDTO(int clinicCode, int doctorCode, int patientCode, String prescriptionCode) {
        this.clinicCode = clinicCode;
        this.doctorCode = doctorCode;
        this.patientCode = patientCode;
        setPrescriptionCode(prescriptionCode); // 빈문자->null 정규화
    }

    public int getClinicCode() {
        return clinicCode;
    }
    public int getDoctorCode() {
        return doctorCode;
    }
    public int getPatientCode() {
        return patientCode;
    }
    public String getPrescriptionCode() {
        return prescriptionCode;
    }

    public void setClinicCode(int clinicCode) {
        this.clinicCode = clinicCode;
    }
    public void setDoctorCode(int doctorCode) {
        this.doctorCode = doctorCode;
    }
    public void setPatientCode(int patientCode) {
        this.patientCode = patientCode;
    }

    public void setPrescriptionCode(String prescriptionCode) {
        this.prescriptionCode = (prescriptionCode == null || prescriptionCode.isBlank())
                ? null
                : prescriptionCode;
    }

    @Override
    public String toString() {
        return "ClinicDTO{" +
                "clinicCode=" + clinicCode +
                ", doctorCode=" + doctorCode +
                ", patientCode=" + patientCode +
                ", prescriptionCode=" + (prescriptionCode == null ? "null" : "'" + prescriptionCode + "'") +
                '}';
    }
}
