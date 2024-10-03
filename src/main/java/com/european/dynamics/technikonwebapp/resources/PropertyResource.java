
package com.european.dynamics.technikonwebapp.resources;

import com.european.dynamics.technikonwebapp.model.Property;
import com.european.dynamics.technikonwebapp.services.PropertyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/properties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropertyResource {

    @Inject
    private PropertyService propertyService;

    @GET
    public Response getAllProperties() {
        List<Property> properties = propertyService.getAll();
        return Response.ok(properties).build();
    }

    @GET
    @Path("/{id}")
    public Response getPropertyById(@PathParam("id") Long id) {
        Optional<Property> property = propertyService.getById(id);
        if (property.isPresent()) {
            return Response.ok(property.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Property not found with ID: " + id)
                    .build();
        }
    }

    @POST
    public Response createProperty(Property property) {
        propertyService.save(property);
        return Response.status(Response.Status.CREATED).entity(property).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProperty(@PathParam("id") Long id, Property updatedProperty) {
    Optional<Property> existingPropertyOpt = propertyService.getById(id);
    if (existingPropertyOpt.isPresent()) {
        Property existingProperty = existingPropertyOpt.get();
        existingProperty.setAddress(updatedProperty.getAddress());
        existingProperty.setYearOfConstruction(updatedProperty.getYearOfConstruction());
        existingProperty.setPropertyType(updatedProperty.getPropertyType());
        propertyService.update(existingProperty);
        return Response.ok(existingProperty).build();
    } else {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Property not found with ID: " + id)
                .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProperty(@PathParam("id") Long id) {
        Optional<Property> existingPropertyOpt = propertyService.getById(id);
        if (existingPropertyOpt.isPresent()) {
            propertyService.deleteById(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Property not found with ID: " + id)
                    .build();
        }
    }

    // Additional endpoints
    @GET
    @Path("/owner/{ownerId}")
    public Response getPropertiesByOwnerId(@PathParam("ownerId") Long ownerId) {
        List<Property> properties = propertyService.findPropertiesByUserId(ownerId);
        return Response.ok(properties).build();
    }
}
