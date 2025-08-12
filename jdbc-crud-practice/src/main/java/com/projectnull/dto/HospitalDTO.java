package com.projectnull.dto;

public class HospitalDTO {

    // patient (환자)
    private String patientCode;     // 환자코드
    private String patientName;     // 환자이름
    private String patientNumber;   // 환자전화번호
    private String symptoms;        // 내원증상

    // clinic (진료)
    private int clinicCode;         // 진료코드
    private int doctorCode;         // 의사코드
    private String prescriptionCode; // 처방코드 (진료에서 FK)

    // doctor (의사)
    private String clinicDay;       // 진료날짜
    private String doctorName;      // 의사이름
    private String specialty;       // 전공과목
    private String clinicHours;     // 진료시간

    // prescription (처방전)
    private String diseaseName;     // 병명

    // pharmaceutical (제약)
    private int pharmaceuticalCode; // 제약코드
    private String drugName;        // 약품명
    private String dosage;          // 복용

    public HospitalDTO() {}

    public HospitalDTO(String patientCode, String dosage, String drugName, int pharmaceuticalCode, String diseaseName, String clinicHours, String specialty, String doctorName, String clinicDay, String prescriptionCode, int doctorCode, int clinicCode, String symptoms, String patientNumber, String patientName) {
        this.patientCode = patientCode;
        this.dosage = dosage;
        this.drugName = drugName;
        this.pharmaceuticalCode = pharmaceuticalCode;
        this.diseaseName = diseaseName;
        this.clinicHours = clinicHours;
        this.specialty = specialty;
        this.doctorName = doctorName;
        this.clinicDay = clinicDay;
        this.prescriptionCode = prescriptionCode;
        this.doctorCode = doctorCode;
        this.clinicCode = clinicCode;
        this.symptoms = symptoms;
        this.patientNumber = patientNumber;
        this.patientName = patientName;

    }


    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public int getClinicCode() {
        return clinicCode;
    }

    public void setClinicCode(int clinicCode) {
        this.clinicCode = clinicCode;
    }

    public int getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(int doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getPrescriptionCode() {
        return prescriptionCode;
    }

    public void setPrescriptionCode(String prescriptionCode) {
        this.prescriptionCode = prescriptionCode;
    }

    public String getClinicDay() {
        return clinicDay;
    }

    public void setClinicDay(String clinicDay) {
        this.clinicDay = clinicDay;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getClinicHours() {
        return clinicHours;
    }

    public void setClinicHours(String clinicHours) {
        this.clinicHours = clinicHours;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getPharmaceuticalCode() {
        return pharmaceuticalCode;
    }

    public void setPharmaceuticalCode(int pharmaceuticalCode) {
        this.pharmaceuticalCode = pharmaceuticalCode;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "HospitalDTO{" +
                "patientCode='" + patientCode + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientNumber='" + patientNumber + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", clinicCode=" + clinicCode +
                ", doctorCode=" + doctorCode +
                ", prescriptionCode='" + prescriptionCode + '\'' +
                ", clinicDay='" + clinicDay + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", clinicHours='" + clinicHours + '\'' +
                ", diseaseName='" + diseaseName + '\'' +
                ", pharmaceuticalCode=" + pharmaceuticalCode +
                ", drugName='" + drugName + '\'' +
                ", dosage='" + dosage + '\'' +
                '}';
    }
}
