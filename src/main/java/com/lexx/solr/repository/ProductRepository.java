package com.lexx.solr.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.lexx.solr.model.Product;

/**
 * Created by alexandru.corghencea on 14-May-19.
 */
public interface ProductRepository extends SolrCrudRepository<Product, String> {

    public List<Product> findByName(String name);

    @Query("id:*?0* OR name:*?0*")
    public Page<Product> findByCustomQuery(String searchTerm, Pageable pageable);

    @Query(name = "Product.findByNamedQuery")
    public Page<Product> findByNamedQuery(String searchTerm, Pageable pageable);

}