package com.lexx.solr.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.lexx.solr.model.Catalog;

/**
 * Created by alexandru.corghencea on 14-May-19.
 */
public interface CatalogRepository extends SolrCrudRepository<Catalog, String> {

//    @Query(fields = { "id" })
//    List<Catalog> findAllCatalogIds();
   // List<Catalog> findByProductId(String name);

}