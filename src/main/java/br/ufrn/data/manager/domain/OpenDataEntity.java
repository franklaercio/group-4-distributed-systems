package br.ufrn.data.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenDataEntity {

    private String id;

    private String data;

    private String database;

    public OpenDataEntity() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public String getDatabase() {
        return database;
    }

    public void setData(String data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.data = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            this.data = null;
        }
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + id + "," + "\"database\":" + database + "," + "\"data\":" + data + "}";
    }
}
