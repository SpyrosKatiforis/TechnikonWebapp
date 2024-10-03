
package com.european.dynamics.technikonwebapp.resources;

import com.european.dynamics.technikonwebapp.model.PropertyRepair;
import com.european.dynamics.technikonwebapp.model.enums.Status;
import com.european.dynamics.technikonwebapp.services.PropertyRepairService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Path("/repairs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropertyRepairResource {

    @Inject
    private PropertyRepairService propertyRepairService;

    @GET
    public Response getAllRepairs() {
        List<PropertyRepair> repairs = propertyRepairService.getAll();
        return Response.ok(repairs).build();
    }

    @GET
    @Path("/{id}")
    public Response getRepairById(@PathParam("id") Long id) {
        Optional<PropertyRepair> repair = propertyRepairService.getById(id);
        if (repair.isPresent()) {
            return Response.ok(repair.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("PropertyRepair not found with ID: " + id)
                    .build();
        }
    }

    @POST
    public Response createRepair(PropertyRepair repair) {
        propertyRepairService.save(repair);
        return Response.status(Response.Status.CREATED).entity(repair).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRepair(@PathParam("id") Long id, PropertyRepair updatedRepair) {
    Optional<PropertyRepair> existingRepairOpt = propertyRepairService.getById(id);
    if (existingRepairOpt.isPresent()) {
        PropertyRepair existingRepair = existingRepairOpt.get();
        // Update fields
        existingRepair.setRepairType(updatedRepair.getRepairType());
        existingRepair.setShortDescription(updatedRepair.getShortDescription());
        existingRepair.setDateSubmitted(updatedRepair.getDateSubmitted());
        existingRepair.setDescription(updatedRepair.getDescription());
        existingRepair.setProposedStartDate(updatedRepair.getProposedStartDate());
        existingRepair.setProposedEndDate(updatedRepair.getProposedEndDate());
        existingRepair.setProposedCost(updatedRepair.getProposedCost());
        existingRepair.setOwnerAcceptance(updatedRepair.getOwnerAcceptance());
        existingRepair.setStatus(updatedRepair.getStatus());
        existingRepair.setActualStartDate(updatedRepair.getActualStartDate());
        existingRepair.setActualEndDate(updatedRepair.getActualEndDate());
        // Update other fields as necessary
        propertyRepairService.update(existingRepair);
        return Response.ok(existingRepair).build();
    } else {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("PropertyRepair not found with ID: " + id)
                .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRepair(@PathParam("id") Long id) {
        Optional<PropertyRepair> existingRepairOpt = propertyRepairService.getById(id);
        if (existingRepairOpt.isPresent()) {
            propertyRepairService.deleteById(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("PropertyRepair not found with ID: " + id)
                    .build();
        }
    }

    // Additional endpoints
    @GET
    @Path("/status/{status}")
    public Response getRepairsByStatus(@PathParam("status") String statusStr) {
        Status status;
        try {
            status = Status.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid status: " + statusStr)
                    .build();
        }
        List<PropertyRepair> repairs = propertyRepairService.findByStatus(status);
        return Response.ok(repairs).build();
    }

    @GET
    @Path("/date-range")
    public Response getRepairsByDateRange(
            @QueryParam("startDate") String startDateStr,
            @QueryParam("endDate") String endDateStr) {
        try {
            LocalDateTime startDate = LocalDateTime.parse(startDateStr);
            LocalDateTime endDate = LocalDateTime.parse(endDateStr);
            List<PropertyRepair> repairs = propertyRepairService.findByDateRange(startDate, endDate);
            return Response.ok(repairs).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format. Expected format: yyyy-MM-ddTHH:mm:ss")
                    .build();
        }
    }

    @GET
    @Path("/property/{propertyId}")
    public Response getRepairsByPropertyId(@PathParam("propertyId") Long propertyId) {
        List<PropertyRepair> repairs = propertyRepairService.findAllByPropertyId(propertyId);
        return Response.ok(repairs).build();
    }
}