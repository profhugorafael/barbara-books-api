package br.com.barbara.controller;

import br.com.barbara.domain.dto.UserRequestDTO;
import br.com.barbara.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;

    @GET
    public Response getAllUsers() {
        var users = userService.getAll();
        return Response.ok(users).build();
    }

    @POST
    public Response createUser(UserRequestDTO userRequestDTO) {
        var userCreatedResponse = userService.createUser(userRequestDTO);

        return Response.status(201).entity(userCreatedResponse).build();
    }
}
