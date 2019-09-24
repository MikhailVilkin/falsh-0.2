package ru.falshwear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.falshwear.model.Product;
import ru.falshwear.persistence.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public void saveProduct(Product product) {
        repository.save(product);
        System.out.println("Added product: " + product.getProductName()
                            + " with id[ " + product.getId() + " ]");
    }

    public void saveProduct(List<Product> products) {
        repository.saveAll(products);
        for(Product product: products)
            System.out.println("Added product: " + product.getProductName()
                                + " with id[ " + product.getId() + " ]");
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(String id) { return repository.findById(id).get(); }

    public void deleteById(String id) {
        System.out.println("Deleting product: " + repository.findById(id).get().getProductName()
                            + " with id[ " + id + " ].........");
        try {
            repository.deleteById(id);
        }
        catch (Exception e) {
            System.out.println("not deleted");
            e.printStackTrace();
        }
    }

    public void change() {

    }
}
