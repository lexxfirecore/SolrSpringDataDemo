package com.lexx.solr.config;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;


/**
 * Created by alexandru.corghencea on 14-May-19.
 */
@Configuration
@EnableSolrRepositories(basePackages = { "com.lexx.solr" }, multicoreSupport = true)
public class SolrConfiguration {

    @Bean
    public SolrServer solrServer(@Value("${solr.host}") String solrHost) {
        return new HttpSolrServer(solrHost);
    }

    @Bean
    public SolrServer solrServerWithCollectionName(@Value("${solr.host}" + "/" + "${solr.collection}") String solrHostWithCollectionName) {
        return new HttpSolrServer(solrHostWithCollectionName);
    }

}