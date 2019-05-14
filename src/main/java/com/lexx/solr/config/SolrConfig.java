package com.lexx.solr.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * Created by alexandru.corghencea on 14-May-19.
 */
@Configuration
@EnableSolrRepositories(
        basePackages = "com.lexx.solr.repository",
        namedQueriesLocation = "classpath:solr-named-queries.properties",
        multicoreSupport = true)
@ComponentScan
public class SolrConfig {

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient("http://localhost:8983/solr");
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }
}