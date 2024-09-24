package todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
class LoadDatabase {
/*
    List<Todo> adminList = new ArrayList<>();

    public LoadDatabase() {


    }

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserProfileRepository repository, TodoRepository repositoryTodo) {
            repositoryTodo.save(new Todo(1l,"test","test","test"));
            repositoryTodo.save(new Todo(1l,"test","test","test"));


        return args -> {
            log.info("Preloading " + repository.save(new UserProfile("admin", "admin",adminList)));
            log.info("Preloading " + repository.save(new UserProfile("user", "user")));

        };
    }*/
}