package tech.buildrun.controller;

import io.vertx.ext.auth.User;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import tech.buildrun.controller.dto.CreateUserDto;
import tech.buildrun.entity.UserEntity;
import tech.buildrun.service.UserService;

import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var users = userService.findAll(page, pageSize);

        return Response.ok(users).build();
    }

    @POST
    @Transactional
    public Response createUser(UserEntity userEntity) {
        return Response.ok(userService.createUser(userEntity)).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") UUID userId) {
        var user = userService.findById(userId);

        return Response.ok(user).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUser(@PathParam("id") UUID userId,
                               UserEntity userEntity) {
        var user = userService.updateUser(userId, userEntity);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") UUID userId) {
        userService.deleteUser(userId);
        return Response.noContent().build();
    }
}
