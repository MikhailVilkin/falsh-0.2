package ru.falshwear.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "product")
public class Product {
    @Id
    private String id;

    private Long creationTime;

    private String productName;
    private String color;
    private String price;
    private String size;
    private Integer amount;

    private Double rating;
    //for counting rating
    private Integer fullRatings;
    private Integer countOfRatings;
    private String description;

    List<String> imagesList;

    public Product(String productName, String color, String price, String size, Integer amount, String description) {

        this.id = UUID.randomUUID().toString().replaceAll("-", "");

        creationTime = (new Date()).getTime();

        this.productName = productName;
        this.color = color;
        this.price = price;
        this.size = size;
        this.amount = amount;

        this.rating = 0.0;
        fullRatings = 0;
        countOfRatings = 0;

        this.description = description;

        imagesList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getColor() {
        return color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getRating() {
        return rating;
    }

    //available to add only one image id or a list of image ids
    public void addImages(String imageId) {
        imagesList.add(imageId);
    }

    public void addImages(List<String> imageIds) {
        imagesList.addAll(imageIds);
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImagesList() { return imagesList; }

    //Using a random exception name
    public void decrementAmount() throws IllegalAccessException {
        if (amount != 0)
            amount--;
        else
            throw new IllegalAccessException("No products exist");
    }

    public void addNewRating(Integer newRating) throws IllegalAccessException {
        if(newRating < 1 || newRating > 5)
            throw new IllegalAccessException("Wrong rating value");
        fullRatings += newRating;
        countOfRatings++;
        this.rating = (double)fullRatings.intValue() / (double)countOfRatings.intValue();
    }

    @Override
    public String toString() {
        return productName + " [id=" + id + "]";
    }

}
