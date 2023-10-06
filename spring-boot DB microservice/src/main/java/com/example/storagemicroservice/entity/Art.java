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


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Art {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;

        private String artGenerationRequestId;
        private LocalDateTime storageTimeStamp;
        private String userId;
        private Object artGenerationParameters;//object can be changed
        private String artPath;//art save location
        private byte[] data;


        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }



        public void setData(byte[] data) {
                this.data = data;
        }


        public byte[] getData() {
                return data;
        }


        public static Art parseArtFromObject(Object object) {
                Art art = null;
                if (object instanceof String) {
                        // parse the art from a JSON string
                        String jsonString = (String) object;
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                                art = mapper.readValue(jsonString, Art.class);
                        } catch (IOException e) {
                                throw new IllegalArgumentException("Can not parse Art from JSON string: " + jsonString);
                        }
                } else if (object instanceof Art) {
                        // the object is already an Art object, so just return it
                        art = (Art) object;
                } else {
                        // handle other types of objects
                        throw new IllegalArgumentException("Unknown object type ");
                }
                return art;
        }


}


