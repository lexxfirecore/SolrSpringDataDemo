package com.lexx.solr;

import org.springframework.beans.factory.annotation.Autowired;

import com.lexx.solr.model.Product;
import com.lexx.solr.repository.ProductRepository;

/**
 * Created by alexandru.corghencea on 14-May-19.
 */
public class Main {

    @Autowired
    private ProductRepository productRepository;


    public static void main(String[] args) {
        System.out.println(Main.class.getName());
        Product product = new Product();
        product.setId("P0001");
        product.setName("Phone");

       // productRepository


    }
}
