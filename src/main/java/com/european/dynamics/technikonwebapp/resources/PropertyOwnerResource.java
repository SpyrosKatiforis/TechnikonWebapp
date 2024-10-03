
package com.european.dynamics.technikonwebapp.resources;

import com.european.dynamics.technikonwebapp.model.PropertyOwner;
import com.european.dynamics.technikonwebapp.services.PropertyOwnerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/owners")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropertyOwnerResource {

    @Inject
    private PropertyOwnerService propertyOwnerService;

    @GET
    public Response getAllOwners() {
        List<PropertyOwner> owners = propertyOwnerService.getAll();
        return Response.ok(owners).build();
    }

    @GET
    @Path("/{id}")
    public Response getOwnerById(@PathParam("id") Long id) {
        Optional<PropertyOwner> owner = propertyOwnerService.getById(id);
        if (owner.isPresent()) {
            return Response.ok(owner.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("PropertyOwner not found with ID: " + id)
                    .build();
        }
    }

    @POST
    public Response createOwner(PropertyOwner owner) {
        propertyOwnerService.save(owner);
        return Response.status(Response.Status.CREATED).entity(owner).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateOwner(@PathParam("id") Long id, PropertyOwner updatedOwner) {
        Optional<PropertyOwner> existingOwnerOpt = propertyOwnerService.getById(id);
        if (existingOwnerOpt.isPresent()) {
            PropertyOwner existingOwner = existingOwnerOpt.get();
            existingOwner.setUsername(updatedOwner.getUsername());
            existingOwner.setEmail(updatedOwner.getEmail());
            existingOwner.setPassword(updatedOwner.getPassword());
            // Update other fields as necessary
            propertyOwnerService.update(existingOwner);
            return Response.ok(existingOwner).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("PropertyOwner not found with ID: " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOwner(@PathParam("id") Long id) {
        Optional<PropertyOwner> existingOwnerOpt = propertyOwnerService.getById(id);
        if (existingOwnerOpt.isPresent()) {
            propertyOwnerService.deleteById(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("PropertyOwner not found with ID: " + id)
                    .build();
        }
    }

    // Additional endpoints
    @GET
    @Path("/username/{username}")
    public Response getOwnerByUsername(@PathParam("username") String username) {
        Optional<PropertyOwner> ownerOpt = propertyOwnerService.findOwnerByUsername(username);
        if (ownerOpt.isPresent()) {
            return Response.ok(ownerOpt.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("PropertyOwner not found with username: " + username)
                    .build();
        }
    }
    
    @POST
    @Path("/login")
    public Response loginOwner(@QueryParam("username") String username, @QueryParam("password") String password) {
    Optional<PropertyOwner> ownerOpt = propertyOwnerService.findOwnerByUsername(username);
    if (ownerOpt.isPresent()) {
        PropertyOwner owner = ownerOpt.get();
        if (propertyOwnerService.validatePropertyOwnerPassword(password, owner)) {
            return Response.ok(owner).build();  // Success, return the property owner object
        }
    }
    return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
}

}