package br.ufrn.data.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenDataEntity {

    private String id;

    private Object data;

    private ResourceEnum resourceEnum;

    public OpenDataEntity(Object data, ResourceEnum resourceEnum) {
        this.data = data;
        this.resourceEnum = resourceEnum;
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

    public ResourceEnum getDatabase() {
        return resourceEnum;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setDatabase(ResourceEnum resourceEnum) {
        this.resourceEnum = resourceEnum;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + id + "," + "\"resourceEnum\":" + resourceEnum + "," + "\"data\":" + data + "}";
    }
}
