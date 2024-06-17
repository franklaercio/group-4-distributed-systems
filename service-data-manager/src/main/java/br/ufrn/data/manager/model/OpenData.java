package br.ufrn.data.manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenData {

    private String id;

    private Object data;

    private Database database;

    public OpenData(Object data, Database database) {
        this.data = data;
        this.database = database;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public Database getDatabase() {
        return database;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + id + "," + "\"database\":" + database + "," + "\"data\":" + data + "}";
    }
}
