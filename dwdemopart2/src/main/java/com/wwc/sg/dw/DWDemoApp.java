package com.wwc.sg.dw;

import com.wwc.sg.dw.resources.IndexResource;
import com.wwc.sg.dw.resources.BookCatalogResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DWDemoApp extends Application<DWDemoAppConfiguration> {

    public static void main(String[] args) throws Exception {
        new DWDemoApp().run(new String[] { "server", "DWDemoApp.yml"});
    }

    @Override
    public void initialize(Bootstrap<DWDemoAppConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new ResourceConfigurationSourceProvider());
    }

    @Override
    public void run(DWDemoAppConfiguration configuration, Environment environment) throws Exception {
        final IndexResource indexResource = new IndexResource(
                configuration.getApplication(),
                configuration.getEnvironment(),
                configuration.getVersion(),
                configuration.getTemplate()
        );
        environment.jersey().register(indexResource);
        environment.jersey().register(new BookCatalogResource());
    }
}
