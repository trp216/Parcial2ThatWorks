package service;

import model.Message;
import model.Task;
import provider.ToDoProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("ToDoTasks")
public class ToDoService {

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Task task){


        try {

            ToDoProvider provider = new ToDoProvider();
            provider.create(task);
            return Response
                    .ok(new Message("Success"))
                    .header("Content-Type","application/json")
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
            return  Response.status(500)
                    .entity(new Message("Failed"))
                    .header("Content-Type","application/json")
                    .build();
        }
    }

    @GET
    @Path("all")
    public Response getAll(){

        try {
            ToDoProvider provider = new ToDoProvider();
            ArrayList<Task> tasks = provider.getAllTasks();
            return Response
                    .ok(tasks)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("operacion fallida"))
                    .header("Content-Type","application/json")
                    .build();
        }
    }


    @PUT
    @Path("edit")
    @Produces("application/json")
    public Response edit (Task task){

        try {
            ToDoProvider provider = new ToDoProvider();
            provider.edit(task);
            return Response
                    .ok(new Message("Operacion exitosa"))
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("operacion fallida"))
                    .header("Content-Type","application/json")
                    .build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") int id){

        try {
            ToDoProvider provider = new ToDoProvider();
            provider.deleteById(id);
            return Response
                    .ok(new Message("Success"))
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("Failed"))
                    .header("Content-Type","application/json")
                    .build();
        }
    }

}
