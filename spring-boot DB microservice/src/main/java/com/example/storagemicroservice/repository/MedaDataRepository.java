package com.example.storagemicroservice.repository;

import com.example.storagemicroservice.entity.Art;
import com.example.storagemicroservice.entity.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedaDataRepository extends JpaRepository<MetaData, String> {
    MetaData save(MetaData data);

    Optional<MetaData> findById(String id);

    void delete(MetaData data);


}
