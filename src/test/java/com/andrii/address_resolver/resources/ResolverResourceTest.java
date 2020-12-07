package com.andrii.address_resolver.resources;

import com.andrii.address_resolver.api.Address;
import com.andrii.address_resolver.api.ResolvedAddress;
import com.andrii.address_resolver.service.ResolverService;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ResolverResourceTest {
    private static final ResolverService service = mock(ResolverService.class);
    private static final ResourceExtension EXT = ResourceExtension.builder()
            .setMapper(Jackson.newObjectMapper())
            .addResource(new ResolverResource(service))
            .build();

    @AfterEach
    void tearDown() {
        reset(service);
    }

    @Test
    public void resolveAddressTest() {
        ResolvedAddress expected = new ResolvedAddress("", "", 1);
        when(service.resolveAddress(any())).thenReturn(expected);

        ResolvedAddress found = EXT.target("/resolver").request().post(Entity.json(new Address("", "", "", "", "")), ResolvedAddress.class);

        assertEquals(expected, found);
        verify(service, times(1)).resolveAddress(any());
    }
}
