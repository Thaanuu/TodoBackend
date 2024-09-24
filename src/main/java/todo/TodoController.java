package todo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("todos")
    List<Todo> all(){
        return todoRepository.findAll().stream().collect(Collectors.toList());

    }

    @GetMapping("/todos/{id}")
    Todo one(@PathVariable Long id){

        Todo todo = todoRepository.findById(id).orElseThrow(()-> new TodoNotFoundException(id));
        return todo;
    }
    @PostMapping("/todos")
    Todo newTodo(@RequestBody Todo todo){
        return todoRepository.save(todo);
    }

    @DeleteMapping("/todos/{id}")
    void deleteTodo(@PathVariable Long id){
        todoRepository.deleteById(id);
    }


}
