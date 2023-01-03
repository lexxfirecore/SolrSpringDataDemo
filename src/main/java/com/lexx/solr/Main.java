package com.lexx.solr;


import com.lexx.solr.model.Catalog;
import com.lexx.solr.repository.CatalogRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;

/**
 * Created by alexandru.corghencea on 14-May-19.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Import({com.lexx.solr.config.SolrConfiguration.class})
public class Main {
    public static final String CATALOG_ID = "1efeeec2-098f-4a33-b562-e59713553d13";

 /*   @Autowired
    private CatalogRepository catalogRepository;
*/

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        CatalogRepository catalogRepository = (CatalogRepository) context.getBean("catalogRepository");
      //  solrRepositoryExample(catalogRepository);

        SolrServer solrServer = (SolrServer) context.getBean("solrServerWithCollectionName");
        solrServerExample(solrServer);
    }

    private static void solrRepositoryExample(CatalogRepository catalogRepository) {

        System.out.println("***** SpringData SOLR Repository");
        try {
            Catalog catalog = catalogRepository.findOne(CATALOG_ID);
            System.out.println("One catalog for " + CATALOG_ID + ": " + catalog);

            Iterable<Catalog> catalogs = catalogRepository.findAll();

            System.out.println("All catalogs: " + catalogs);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void solrServerExample(SolrServer solrServer) {

        System.out.println("***** SOLR Query with Explain analyze");

        SolrQuery solrQuery = new SolrQuery();

        solrQuery.set("q", "productId:10008 OR skuId:20004");
        solrQuery.set("debugQuery", true);
        solrQuery.setFields("id", "skuId", "productId");

        try {
            QueryResponse queryResponse = solrServer.query(solrQuery);
            SolrDocumentList results = queryResponse.getResults();
            System.out.println("Results: " + results.get(0));

            Map<String, Object> debugMap = queryResponse.getDebugMap();
            System.out.println("debugMap: " + debugMap);

            Map<String, String> explainMap = queryResponse.getExplainMap();
            System.out.println("explainMap: " + explainMap.entrySet().stream().findFirst());

            for (Map.Entry<String, String> entry : explainMap.entrySet()) {

                String criteria = StringUtils.substringBetween(entry.getValue(), "weight(", ":");
                System.out.println("key: " + entry.getKey() + " -> found by criteria: " + criteria);

            }


        } catch (SolrServerException e) {
            throw new RuntimeException(e);
        }
    }


}
