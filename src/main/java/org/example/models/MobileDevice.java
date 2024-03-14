package org.example.models;

public class MobileDevice {
    private int id;
    private int imeiNumber;
    private String phonebrand;
    private String modelNumber;

    public MobileDevice() {
    }

    public MobileDevice(int imeiNumber, String phonebrand, String modelNumber) {
        this.imeiNumber = imeiNumber;
        this.phonebrand = phonebrand;
        this.modelNumber = modelNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImeiNumber() {
        return imeiNumber;
    }

    public void setImeiNumber(int imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    public String getPhonebrand() {
        return phonebrand;
    }

    public void setPhonebrand(String phonebrand) {
        this.phonebrand = phonebrand;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @Override
    public String toString() {
        return "MobileDevice{" +
                "id=" + id +
                ", imeiNumber=" + imeiNumber +
                ", phonebrand='" + phonebrand + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                '}';
    }

}

