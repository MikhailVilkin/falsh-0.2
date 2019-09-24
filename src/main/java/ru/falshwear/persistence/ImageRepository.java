package ru.falshwear.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.falshwear.model.Image;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
}
