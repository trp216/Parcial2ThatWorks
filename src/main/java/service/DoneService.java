package service;

import db.MySQLConnection;
import model.Message;
import model.Task;
import provider.DoneProvider;
import provider.ToDoProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Path("DoneTasks")
public class DoneService {


    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Task task){


        try {

            DoneProvider provider = new DoneProvider();
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
            DoneProvider provider = new DoneProvider();
            ArrayList<Task> tasks = provider.getAllTasks();
            return Response
                    .ok(tasks)
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

    @DELETE
    @Path("delete/{id}")
    @Produces("application/json")
    public Response delete(@PathParam("id") int id){

        try {
            DoneProvider provider = new DoneProvider();
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
