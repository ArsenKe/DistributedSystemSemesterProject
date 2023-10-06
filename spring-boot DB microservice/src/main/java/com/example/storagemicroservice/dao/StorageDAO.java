package com.example.storagemicroservice.dao;

import com.example.storagemicroservice.entity.Art;
import com.example.storagemicroservice.entity.MetaData;
import com.example.storagemicroservice.repository.MedaDataRepository;
import com.example.storagemicroservice.service.StorageService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StorageDAO implements StorageService {
    private final MedaDataRepository medaDataRepository;
    private final File fileSystem;

    @Override
    public void storeArtAndMetaData(Object object) {
        Art art = Art.parseArtFromObject(object);
        MetaData metadata = MetaData.parseMetaDataFromObject(object);
        if (art != null && metadata != null) {
            // store the art and metadata in the database and file system
            medaDataRepository.save(metadata);
            fileSystem.saveArt(art);
        } else {
            throw new IllegalArgumentException("Can not store data ");
        }
    }


    @Override
    public void deleteArtAndMetaData(String artId) {
        // retrieve the metadata for the specified artId
        MetaData metadata = medaDataRepository.findById(artId).orElse(null);
        if (metadata != null) {
            // delete the art from the file system
            fileSystem.deleteArt(metadata.getArtPath());
            // delete the metadata from the repository
            medaDataRepository.deleteById(artId);
        } else {
            throw new IllegalArgumentException("Can not delete data " );

        }
    }

}
