package org.example.models;


import java.sql.Date;
import java.time.LocalDateTime;

public class Reparation {
    private int id;
    private int customerId;
    private int mobileDeviceId;
    private String employeeFirstName;
    private String employeeLastName;
    private int employeeNumber;
    private Date reparationStartDate;
    private Date reparationEndDate;
    private String reparationStatus;
    private String reparationDescription;
    private String images;

    public Reparation() {
    }

    public Reparation(int customerId, int mobileDeviceId, String employeeFirstName, String employeeLastName, int employeeNumber, Date reparationStartDate, Date reparationEndDate, String reparationStatus, String reparationDescription, String images) {
        this.customerId = customerId;
        this.mobileDeviceId = mobileDeviceId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeNumber = employeeNumber;
        this.reparationStartDate = reparationStartDate;
        this.reparationEndDate = reparationEndDate;
        this.reparationStatus = reparationStatus;
        this.reparationDescription = reparationDescription;
        this.images = images;
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

    public Date getReparationStartDate() {
        return reparationStartDate;
    }

    public void setReparationStartDate(Date reparationStartDate) {
        this.reparationStartDate = reparationStartDate;
    }

    public Date getReparationEndDate() {
        return reparationEndDate;
    }

    public void setReparationEndDate(Date reparationEndDate) {
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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
                ", images='" + images + '\'' +
                '}';
    }
}