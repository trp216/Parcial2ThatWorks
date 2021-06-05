package config;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> recursos = new HashSet<>();

        recursos.add(service.ToDoService.class);
        recursos.add(service.DoingService.class);
        recursos.add(service.DoneService.class);

        return recursos;
    }
}
