package com.lexx.solr.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import com.lexx.solr.config.SolrConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.lexx.solr.model.Catalog;
import com.lexx.solr.repository.CatalogRepository;

/**
 * Created by alexandru.corghencea on 14-May-19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SolrConfiguration.class)
public class CatalogRepositoryLiveTest {

    @Autowired
    private CatalogRepository catalogRepository;

    @Before
    public void clearSolrData() {
        catalogRepository.deleteAll();
    }

    @Test
    public void whenSavingProduct_thenAvailableOnRetrieval() throws Exception {
        final Catalog catalog = new Catalog();
        catalog.setId("P000089998");
        catalog.setProductId("Desk");
        catalogRepository.save(catalog);
        final Catalog retrievedCatalog = catalogRepository.findOne(catalog.getId());
        assertEquals(catalog.getId(), retrievedCatalog.getId());
    }

    @Test
    public void whenUpdatingProduct_thenChangeAvailableOnRetrieval() throws Exception {
        final Catalog catalog = new Catalog();
        catalog.setId("P0001");
        catalog.setProductId("T-Shirt");

        catalogRepository.save(catalog);

        catalog.setProductId("Shirt");
        catalogRepository.save(catalog);

        final Catalog retrievedCatalog = catalogRepository.findOne(catalog.getId());
        assertEquals(catalog.getProductId(), retrievedCatalog.getProductId());
    }

    @Test
    public void whenDeletingProduct_thenNotAvailableOnRetrieval() throws Exception {
        final Catalog catalog = new Catalog();
        catalog.setId("P0001");
        catalog.setProductId("Desk");
        catalogRepository.save(catalog);

        catalogRepository.delete(catalog);

        Catalog retrievedCatalog = catalogRepository.findOne(catalog.getId());
        assertNull(retrievedCatalog);

    }

    @Test
    public void whenFindByName_thenAvailableOnRetrieval() throws Exception {
        Catalog phone = new Catalog();
        phone.setId("P0001");
        phone.setProductId("Phone");
        catalogRepository.save(phone);

        List<Catalog> retrievedCatalogs = catalogRepository.findByProductId("4052");
        assertEquals(phone.getId(), retrievedCatalogs.get(0).getId());
    }

    @Test
    public void whenSearchingProductsByQuery_thenAllMatchingProductsShouldAvialble() throws Exception {
        final Catalog phone = new Catalog();
        phone.setId("P0001");
        phone.setProductId("Smart Phone");
        catalogRepository.save(phone);

        final Catalog phoneCover = new Catalog();
        phoneCover.setId("P0002");
        phoneCover.setProductId("Phone Cover");
        catalogRepository.save(phoneCover);

        final Catalog wirelessCharger = new Catalog();
        wirelessCharger.setId("P0003");
        wirelessCharger.setProductId("Phone Charging Cable");
        catalogRepository.save(wirelessCharger);

        Page<Catalog> result = catalogRepository.findByCustomQuery("Phone", new PageRequest(0, 10));
        assertEquals(3, result.getNumberOfElements());
    }

    @Test
    public void whenSearchingProductsByNamedQuery_thenAllMatchingProductsShouldAvialble() throws Exception {
        final Catalog phone = new Catalog();
        phone.setId("P0001");
        phone.setProductId("Smart Phone");
        catalogRepository.save(phone);

        final Catalog phoneCover = new Catalog();
        phoneCover.setId("P0002");
        phoneCover.setProductId("Phone Cover");
        catalogRepository.save(phoneCover);

        final Catalog wirelessCharger = new Catalog();
        wirelessCharger.setId("P0003");
        wirelessCharger.setProductId("Phone Charging Cable");
        catalogRepository.save(wirelessCharger);

        Page<Catalog> result = catalogRepository.findByNamedQuery("one", new PageRequest(0, 10));
        assertEquals(3, result.getNumberOfElements());
    }

}