package com.andrii.address_resolver.resources;

import com.andrii.address_resolver.api.Address;
import com.andrii.address_resolver.api.ResolvedAddress;
import com.andrii.address_resolver.service.ResolverService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/resolver")
@Produces(MediaType.APPLICATION_JSON)
public class ResolverResource {

    private final ResolverService resolverService;

    public ResolverResource(ResolverService resolverService) {
        this.resolverService = resolverService;
    }

    @POST
    public ResolvedAddress resolveAddress(Address address) {
        return resolverService.resolveAddress(address);
    }
}
