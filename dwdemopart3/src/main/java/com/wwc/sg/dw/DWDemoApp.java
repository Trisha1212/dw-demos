package com.wwc.sg.dw;

import com.wwc.sg.dw.dao.BookDAO;
import com.wwc.sg.dw.representations.Book;
import com.wwc.sg.dw.resources.IndexResource;
import com.wwc.sg.dw.resources.BookCatalogResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DWDemoApp extends Application<DWDemoAppConfiguration> {

    /**
     * Hibernate bundle.
     */
    private final HibernateBundle<DWDemoAppConfiguration> hibernateBundle = new HibernateBundle<DWDemoAppConfiguration>(
                Book.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(
                DWDemoAppConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new DWDemoApp().run(new String[] { "server", "DWDemoApp.yml"});
    }

    @Override
    public void initialize(Bootstrap<DWDemoAppConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new ResourceConfigurationSourceProvider());
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(DWDemoAppConfiguration configuration, Environment environment) throws Exception {
        final BookDAO bookDAO
                = new BookDAO(hibernateBundle.getSessionFactory());

        final IndexResource indexResource = new IndexResource(
                configuration.getApplication(),
                configuration.getEnvironment(),
                configuration.getVersion(),
                configuration.getTemplate()
        );
        environment.jersey().register(indexResource);
        environment.jersey().register(new BookCatalogResource(bookDAO));
    }
}
