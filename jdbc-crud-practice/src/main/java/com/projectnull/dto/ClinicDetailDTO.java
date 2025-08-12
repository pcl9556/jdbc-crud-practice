package com.projectnull.dto;

public class ClinicDetailDTO {
    private int clinicCode;
    private int doctorCode;
    private String doctorName;
    private String specialty;
    private String clinicDay;
    private String clinicHours;

    private int patientCode;
    private String patientName;
    private String patientNumber;
    private String symptoms;

    private String prescriptionCode;
    private String diseaseName;

    private Integer pharmaceuticalCode; // null 가능
    private String drugName;
    private String dosage;

    public ClinicDetailDTO() {}

    public int getClinicCode() {
        return clinicCode;
    }
    public int getDoctorCode() {
        return doctorCode;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public String getSpecialty() {
        return specialty;
    }
    public String getClinicDay() {
        return clinicDay;
    }
    public String getClinicHours() {
        return clinicHours;
    }
    public int getPatientCode() {
        return patientCode;
    }
    public String getPatientName() {
        return patientName;
    }
    public String getPatientNumber() {
        return patientNumber;
    }
    public String getSymptoms() {
        return symptoms;
    }
    public String getPrescriptionCode() {
        return prescriptionCode;
    }
    public String getDiseaseName() {
        return diseaseName;
    }
    public Integer getPharmaceuticalCode() {
        return pharmaceuticalCode;
    }
    public String getDrugName() {
        return drugName;
    }
    public String getDosage() {
        return dosage;
    }

    
    public void setClinicCode(int clinicCode) { this.clinicCode = clinicCode; }
    public void setDoctorCode(int doctorCode) { this.doctorCode = doctorCode; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public void setClinicDay(String clinicDay) { this.clinicDay = clinicDay; }
    public void setClinicHours(String clinicHours) { this.clinicHours = clinicHours; }
    public void setPatientCode(int patientCode) { this.patientCode = patientCode; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public void setPatientNumber(String patientNumber) { this.patientNumber = patientNumber; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public void setPrescriptionCode(String prescriptionCode) { this.prescriptionCode = prescriptionCode; }
    public void setDiseaseName(String diseaseName) { this.diseaseName = diseaseName; }
    public void setPharmaceuticalCode(Integer pharmaceuticalCode) { this.pharmaceuticalCode = pharmaceuticalCode; }
    public void setDrugName(String drugName) { this.drugName = drugName; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    @Override
    public String toString() {
        return "ClinicDetailDTO{" +
                "clinicCode=" + clinicCode +
                ", doctor=" + doctorCode + " (" + doctorName + ", " + specialty + ")" +
                ", clinicDay='" + clinicDay + '\'' +
                ", clinicHours='" + clinicHours + '\'' +
                ", patient=" + patientCode + " (" + patientName + ", " + patientNumber + ", " + symptoms + ")" +
                ", prescriptionCode='" + prescriptionCode + '\'' +
                ", diseaseName='" + diseaseName + '\'' +
                ", pharmaceuticalCode=" + pharmaceuticalCode +
                ", drugName='" + drugName + '\'' +
                ", dosage='" + dosage + '\'' +
                '}';
    }
}

