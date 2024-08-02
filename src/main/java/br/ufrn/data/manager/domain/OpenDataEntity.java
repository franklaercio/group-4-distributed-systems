package br.ufrn.data.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenDataEntity {

    private String id;

    @JsonDeserialize(using = JsonToStringDeserializer.class)
    @JsonSerialize(using = JsonToStringSerializer.class)
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

class JsonToStringDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, com.fasterxml.jackson.databind.DeserializationContext deserializationContext) {
        ObjectMapper mapper = new ObjectMapper();
        Object json = null;
        try {
            json = mapper.readTree(jsonParser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json.toString();
    }
}

class JsonToStringSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, com.fasterxml.jackson.core.JsonGenerator jsonGenerator, com.fasterxml.jackson.databind.SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeRawValue(value);
    }
}
