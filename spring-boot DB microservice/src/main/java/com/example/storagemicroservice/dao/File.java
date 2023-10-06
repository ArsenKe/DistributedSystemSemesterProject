package com.example.storagemicroservice.dao;
import com.example.storagemicroservice.entity.Art;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File {

    private Path root;

    public File(String root) {
        this.root = Paths.get(root);
    }

    public void saveArt(Art art) {
        Path filePath = root.resolve(art.getId());
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, art.getData());
        } catch (IOException e) {
            throw new IllegalArgumentException("Can not save art file " + filePath);
        }
    }

    public void deleteArt(String artPath) {
        Path filePath = root.resolve(artPath);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new IllegalArgumentException("Can not delete file " + filePath);

        }
    }

}
