package com.example.nasaapiproject;
public class Photo {

    private String id;

    private String sol;

    private String cameraId;

    private String cameraName;

    private String roverId;

    private String imgSrc;

    private String date;

    private String roverName;

    private String roverLandingD;

    private String roverLaunchD;

    private String roverStatus;


    public Photo(){

    }

    public void setId(String id) {
        this.id = id;
    }
    public void setSol(String sol) {
        this.sol = sol;
    }
    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }
    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }
    public void setRoverId(String roverId) {
        this.roverId = roverId;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setLaunchD(String lD) {
        this.roverLaunchD = lD;
    }
    public void setLandD(String ld) {
        this.roverLandingD = ld;
    }
    public void setRoverStatus(String roverS) {
        this.roverStatus = roverS;
    }
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }

    public String getId() {
        return id;
    }
    public String getSol() {
        return sol;
    }
    public String getCameraId() {
        return cameraId;
    }
    public String getCameraName() {
        return cameraName;
    }
    public String getRoverId() {
        return roverId;
    }
    public String getDate() {
        return date;
    }
    public String getLaunchD() {
        return roverLaunchD;
    }
    public String getLandD() {
        return roverLandingD;
    }
    public String getRoverStatus() {
        return roverStatus;
    }
    public String getImgSrc() {
        return imgSrc;
    }
    public String getRoverName() {
        return roverName;
    }}