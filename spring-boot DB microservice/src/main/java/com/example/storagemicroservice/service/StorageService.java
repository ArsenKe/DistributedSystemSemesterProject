package com.example.storagemicroservice.service;

public interface StorageService {
    //object can be changed(object is a payload)
    public void storeArtAndMetaData(Object object);

    public void deleteArtAndMetaData(String artId);
}
