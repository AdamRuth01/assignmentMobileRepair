package org.example.models;


import java.time.LocalDateTime;

public class Reparation {
    private int id;
    private int customerId;
    private int mobileDeviceId;
    private String employeeFirstName;
    private String employeeLastName;
    private int employeeNumber;
    private LocalDateTime  reparationStartDate;
    private LocalDateTime reparationEndDate;
    private String reparationStatus;
    private String reparationDescription;


    public Reparation() {
    }

    public Reparation(int customerId, int mobileDeviceId, String employeeFirstName, String employeeLastName, int employeeNumber, LocalDateTime reparationStartDate, LocalDateTime reparationEndDate, String reparationStatus, String reparationDescription) {
        this.customerId = customerId;
        this.mobileDeviceId = mobileDeviceId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeNumber = employeeNumber;
        this.reparationStartDate = reparationStartDate;
        this.reparationEndDate = reparationEndDate;
        this.reparationStatus = reparationStatus;
        this.reparationDescription = reparationDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getMobileDeviceId() {
        return mobileDeviceId;
    }

    public void setMobileDeviceId(int mobileDeviceId) {
        this.mobileDeviceId = mobileDeviceId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public LocalDateTime getReparationStartDate() {
        return reparationStartDate;
    }

    public void setReparationStartDate(LocalDateTime reparationStartDate) {
        this.reparationStartDate = reparationStartDate;
    }

    public LocalDateTime getReparationEndDate() {
        return reparationEndDate;
    }

    public void setReparationEndDate(LocalDateTime reparationEndDate) {
        this.reparationEndDate = reparationEndDate;
    }

    public String getReparationStatus() {
        return reparationStatus;
    }

    public void setReparationStatus(String reparationStatus) {
        this.reparationStatus = reparationStatus;
    }

    public String getReparationDescription() {
        return reparationDescription;
    }

    public void setReparationDescription(String reparationDescription) {
        this.reparationDescription = reparationDescription;
    }

    @Override
    public String toString() {
        return "Reparation{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", mobileDeviceId=" + mobileDeviceId +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", employeeNumber=" + employeeNumber +
                ", reparationStartDate=" + reparationStartDate +
                ", reparationEndDate=" + reparationEndDate +
                ", reparationStatus='" + reparationStatus + '\'' +
                ", reparationDescription='" + reparationDescription + '\'' +
                '}';
    }
}
