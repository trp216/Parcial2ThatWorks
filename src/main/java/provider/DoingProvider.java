package provider;

import db.MySQLConnection;
import model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DoingProvider {

    public void create(Task task) throws SQLException {
        Date fecha = new Date();
        // se ha de poner la tabla que es de to do
        String sql =  "INSERT INTO A00358235DoingTasks(nombre,  descripcion,  fecha) VALUES ('$NOMBRE','$DESCRIPCION','$FECHA')";
        sql = sql.replace("$NOMBRE", task.getNombre());
        sql = sql.replace("$DESCRIPCION", task.getDescripcion());
        sql = sql.replace("$FECHA", ""+fecha.toString());
        MySQLConnection connection =  new MySQLConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();

    }

    public ArrayList<Task> getAllTasks() throws SQLException {

        ArrayList <Task> output = new ArrayList<>();

        String sql = "SELECT * FROM A00358235DoingTasks";
        MySQLConnection connection = new MySQLConnection();
        connection.connect();

        ResultSet resultSet = connection.getDataBySQL(sql);
        while (resultSet.next()){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            String nombre = resultSet.getString(resultSet.findColumn("nombre"));
            String description = resultSet.getString(resultSet.findColumn("descripcion"));
            String fecha = resultSet.getString(resultSet.findColumn("fecha"));

            Task task = new Task(id,nombre,description,fecha);
            output.add(task);
        }
        connection.disconnect();

        return output;
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM A00358235DoingTasks WHERE id ="+id;
        MySQLConnection connection =  new MySQLConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }
}
