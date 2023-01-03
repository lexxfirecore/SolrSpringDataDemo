package com.lexx.solr;



import com.lexx.solr.model.Catalog;
import com.lexx.solr.repository.CatalogRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by alexandru.corghencea on 14-May-19.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Import({ com.lexx.solr.config.SolrConfiguration.class})
public class Main {

 /*   @Autowired
    private CatalogRepository catalogRepository;
*/

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        CatalogRepository catalogRepository = (CatalogRepository) context.getBean("catalogRepository");


        try {
            Catalog catalog = catalogRepository.findOne("1efeeec2-098f-4a33-b562-e59713553d13");
            System.out.println("catalogs: " + catalog);

            Iterable<Catalog> catalogs = catalogRepository.findAll();


                System.out.println("catalogs: " + catalogs);

        } catch (Exception e) {
            System.out.println(e);
        }

    }


}
