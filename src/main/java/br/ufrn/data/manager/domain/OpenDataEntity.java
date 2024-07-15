package br.ufrn.data.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenDataEntity {

    private String id;

    private Object data;

    private DatabaseEntity databaseEntity;

    public OpenDataEntity(Object data, DatabaseEntity databaseEntity) {
        this.data = data;
        this.databaseEntity = databaseEntity;
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

    public DatabaseEntity getDatabase() {
        return databaseEntity;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setDatabase(DatabaseEntity databaseEntity) {
        this.databaseEntity = databaseEntity;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + id + "," + "\"databaseEntity\":" + databaseEntity + "," + "\"data\":" + data + "}";
    }
}
