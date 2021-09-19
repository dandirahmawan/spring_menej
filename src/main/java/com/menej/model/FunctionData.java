package com.menej.model;


public class FunctionData {
    int id;
    String name;
    String type;
    String docUrl;

    public FunctionData(int id, String name, String type, String docUrl){
        this.id = id;
        this.name = name;
        this.type = type;
        this.docUrl = docUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }
}
