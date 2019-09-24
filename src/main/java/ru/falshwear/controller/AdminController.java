package ru.falshwear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.falshwear.model.Product;
import ru.falshwear.service.ImageService;
import ru.falshwear.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/adminPage")
public class AdminController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @RequestMapping
    public String adminPage(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/addProduct")
    public String addProductPage(Model model) {
        return "adminPages/addProduct";
    }

    @PostMapping("/addProduct/submit")
    public String addProductButtonPushed(@RequestParam("productName") String productName,
                                         @RequestParam("color") String color,
                                         @RequestParam("price") String price,
                                         @RequestParam("size") String size,
                                         @RequestParam("amount") Integer amount,
                                         @RequestParam("description") String description,
                                         @RequestParam("images") MultipartFile[] images,
                                         Model model) throws IOException{

        List<String> imageIds = new ArrayList<>();
        int i = 0;
        for(MultipartFile image: images) {
            i++;
            try {
                imageIds.add(imageService.addImage(productName + "_" + i, image));
            } catch (IOException e) {
                i--;
                e.printStackTrace();
            }
        }

        Product product = new Product(productName, color, price, size, amount, description);
        product.addImages(imageIds);
        productService.saveProduct(product);

        return "redirect:/adminPage/seeProducts";
    }

    @GetMapping("/seeProduct/{id}")
    public String seeOneProduct(@PathVariable String id, Model model) {

        Product product = productService.findById(id);
        model.addAttribute("product", product);

        List<String> images = product.getImagesList();
        List<String> byteImages = new ArrayList<>();
        for(String image: images) {
            byteImages.add(Base64.getEncoder().encodeToString(imageService.getImage(image).getImage().getData()));
        }

        model.addAttribute("images", byteImages);

        return "adminPages/product";
    }

    @RequestMapping(value = "/seeProducts/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable String id, Model model) {

        productService.deleteById(id);
        return "redirect:/adminPage/seeProducts";
    }

    @RequestMapping(value = "/seeProducts")
    public String seeProductsPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "adminPages/seeProducts";
    }
}
