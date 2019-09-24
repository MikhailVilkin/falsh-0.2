package ru.falshwear.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "images")
public class Image {
    @Id
    private String id;

    private String title;
    private Binary image;
    private Long creationTimestamp;

    public Image(String title) {
        this.title = title;
        creationTimestamp = (new Date()).getTime();
        id = creationTimestamp + "";
    }

    public String getTitle() {
        return title;
    }

    public Binary getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Binary image) {
        this.image = image;
    }

    public Long getCreationTimestamp() {
        return creationTimestamp;
    }
}
