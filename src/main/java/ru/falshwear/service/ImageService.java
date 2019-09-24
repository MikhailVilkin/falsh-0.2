package ru.falshwear.service;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.falshwear.model.Image;
import ru.falshwear.persistence.ImageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String addImage(String title, MultipartFile file) throws IOException {
        Image image = new Image(title);
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image = imageRepository.insert(image);
        System.out.println("Added image: " + image.getTitle() + " with id[ " + image.getId() + " ]");
        return image.getId();
    }

    public Image getImage(String id) {
        return imageRepository.findById(id).get();
    }

    public List<Image> getImages(List<String> ids) {
        List<Image> images = new ArrayList<>();

        for(String id: ids) {
            images.add(imageRepository.findById(id).get());
        }

        return images;
    }

    public List<Image> getAll() {
        return imageRepository.findAll();
    }
}
