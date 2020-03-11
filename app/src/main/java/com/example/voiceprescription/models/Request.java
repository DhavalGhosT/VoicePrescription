package com.example.voiceprescription.models;

import com.google.firebase.Timestamp;

public class Request {
    String from, docUrl,fileUrl;
    Timestamp timeStamp;

    public Request() {
    }

    public Request(String from, String docUrl,String fileUrl, Timestamp timeStamp) {
        this.from = from;
        this.docUrl = docUrl;
        this.fileUrl=fileUrl;
        this.timeStamp = timeStamp;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    public  String getFileUrl(){
        return  fileUrl;
    }

    public void setFileUrl(String fileUrl){
        this.fileUrl=fileUrl;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}


