
package com.european.dynamics.technikonwebapp.resources;


import com.european.dynamics.technikonwebapp.model.Admin;
import com.european.dynamics.technikonwebapp.services.AdminService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/admins")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {

    @Inject
    private AdminService adminService;

    @GET
    public Response getAllAdmins() {
        List<Admin> admins = adminService.getAll();
        return Response.ok(admins).build();
    }

    @GET
    @Path("/{id}")
    public Response getAdminById(@PathParam("id") Long id) {
        Optional<Admin> admin = adminService.getById(id);
        if (admin.isPresent()) {
            return Response.ok(admin.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Admin not found with ID: " + id)
                    .build();
        }
    }

    @POST
    public Response createAdmin(Admin admin) {
        adminService.save(admin);
        return Response.status(Response.Status.CREATED).entity(admin).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAdmin(@PathParam("id") Long id, Admin updatedAdmin) {
        Optional<Admin> existingAdminOpt = adminService.getById(id);
        if (existingAdminOpt.isPresent()) {
            Admin existingAdmin = existingAdminOpt.get();
            existingAdmin.setUsername(updatedAdmin.getUsername());
            existingAdmin.setEmail(updatedAdmin.getEmail());
            existingAdmin.setPassword(updatedAdmin.getPassword());
            adminService.update(existingAdmin);
            return Response.ok(existingAdmin).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Admin not found with ID: " + id)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAdmin(@PathParam("id") Long id) {
        Optional<Admin> existingAdminOpt = adminService.getById(id);
        if (existingAdminOpt.isPresent()) {
            adminService.deleteById(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Admin not found with ID: " + id)
                    .build();
        }
    }

    @POST
    @Path("/login")
    public Response loginAdmin(@QueryParam("username") String username, @QueryParam("password") String password) {
        Optional<Admin> adminOpt = adminService.findAdminByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (adminService.validateAdminPassword(password, admin)) {
                return Response.ok(admin).build();  // Success, return the admin object
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid username or password").build();
    }

}