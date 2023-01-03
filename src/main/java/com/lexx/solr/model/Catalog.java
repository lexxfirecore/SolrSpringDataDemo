package com.lexx.solr.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Created by alexandru.corghencea on 14-May-19.
 */
@SolrDocument(solrCoreName = "catalog")
public class Catalog {

    @Id
    @Indexed("id")
    private String id;

    @Indexed("productId")
    private String productId;

    @Indexed
    private String skuId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        return "id: " + id + ", productId:" + productId + ", skuId: " +skuId;
    }
}