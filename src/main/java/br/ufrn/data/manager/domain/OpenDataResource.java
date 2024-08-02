package br.ufrn.data.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenDataResource {

    private String id;

    private Object data;

    private String database;

    public OpenDataResource() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public String getDatabase() {
        return database;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + id + "," + "\"database\":" + database + "," + "\"data\":" + data + "}";
    }
}
