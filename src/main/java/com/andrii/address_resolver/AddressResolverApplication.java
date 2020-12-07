package com.andrii.address_resolver;

import com.andrii.address_resolver.config.AddressResolverConfiguration;
import com.andrii.address_resolver.resources.ResolverResource;
import com.andrii.address_resolver.service.HereComRepository;
import com.andrii.address_resolver.service.ResolverService;
import com.google.common.collect.ImmutableList;
import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHeader;

public class AddressResolverApplication extends Application<AddressResolverConfiguration> {
    public static void main(String[] args) throws Exception {
        new AddressResolverApplication().run("server", "application.yml");
    }

    @Override
    public void run(AddressResolverConfiguration config, Environment environment) {
        final HttpClient httpClient = new HttpClientBuilder(environment)
                .using(ImmutableList.<Header>of(new BasicHeader("Accept", "application/json")))
                .using(config.getHttpClient())
                .build(getName());
        final HereComRepository repository = new HereComRepository(environment.getObjectMapper(), httpClient, config.getHereCom());
        final ResolverService service = new ResolverService(repository);
        final ResolverResource resource = new ResolverResource(service);
        environment
                .jersey()
                .register(resource);
    }

    @Override
    public void initialize(Bootstrap<AddressResolverConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
        super.initialize(bootstrap);
    }
}
