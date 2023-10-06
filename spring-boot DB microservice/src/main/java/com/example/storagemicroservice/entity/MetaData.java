package com.example.storagemicroservice.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String artGenerationRequestId;
    private LocalDateTime storageTimeStamp;
    private String userId;
    private Object artGenerationParameters;
    private String artPath;

    public static MetaData parseMetaDataFromObject(Object object) {
        MetaData metadata = null;
        if (object instanceof String) {
            // parse the metadata from a JSON string
            String jsonString = (String) object;
            ObjectMapper mapper = new ObjectMapper();
            try {
                metadata = mapper.readValue(jsonString, MetaData.class);
            } catch (IOException e) {
                throw new IllegalArgumentException("Can not parse Metadata from JSON string: " + jsonString);

            }
        } else if (object instanceof Map) {
            // parse the metadata from a map
            Map<String, Object> map = (Map<String, Object>) object;
            metadata = new MetaData();
            metadata.setId((String) map.get("id"));
            metadata.setArtGenerationRequestId((String) map.get("artGenerationRequestId"));
            metadata.setStorageTimeStamp(LocalDateTime.parse((String) map.get("storageTimeStamp")));
            metadata.setUserId((String) map.get("userId"));
            metadata.setArtGenerationParameters(map.get("artGenerationParameters"));
            metadata.setArtPath((String) map.get("artPath"));
        } else {
            throw new IllegalArgumentException("Can not parse Metadata from Map ");
        }
        {
            metadata = (MetaData) object;
        }
        return metadata;
    }


}