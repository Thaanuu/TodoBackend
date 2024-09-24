package todo;

import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
public class UserProfileController {
        private final UserProfileRepository repository;
    private  final TodoRepository todoRepository;

    UserProfileController( UserProfileRepository repository, TodoRepository todoRepository){
        this.repository = repository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/userprofiles")
    List<UserProfile> all(){
        return repository.findAll().stream().collect(Collectors.toList());
    }


    @GetMapping("/userprofiles/{id}")
    UserProfile one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserProfileNotFoundException(id));
    }

    @GetMapping("/userprofiles/{id}/todos")
    List<Todo> allTodos(@PathVariable Long id){
        List<Todo> todos = todoRepository.findAll().stream().filter(todo -> todo.getUserId() == id).collect(Collectors.toList());

        return todos;
    }

    @GetMapping("/userprofiles/{id}/todos/{todoId}")
    Todo oneTodo(@PathVariable Long id, @PathVariable Long todoId){
        List<Todo> todos = todoRepository.findAll().stream().filter(todo -> todo.getUserId() == id && todo.getId() == todoId).collect(Collectors.toList());

        return todos.get(0);
    }

    @PostMapping("/userprofiles/{id}/todos")
    Todo newTodo(@RequestBody Todo newTodo, @PathVariable Long id){
        Todo tempTodo = new Todo(id,newTodo.getTodoText(),newTodo.getDescription(), newTodo.getDeadline());
        try{
            if(getDayDiff(tempTodo.getDeadline()) > 0 && tempTodo.getStatus() != "completed"){
                System.out.println();
                tempTodo.setStatus("overdue");
            }} catch (DateTimeParseException e){
            tempTodo.setDeadline("None");
        }

        return todoRepository.save(tempTodo);
    }

    @PutMapping("/userprofiles/{id}/todos/{todoId}")
    Todo replaceTodo(@RequestBody Todo newTodo, @PathVariable Long id, @PathVariable Long todoId){
        return todoRepository.findById(todoId).map(todo ->{
          todo.setTodoText(newTodo.getTodoText());
          todo.setDescription(newTodo.getDescription());
          todo.setDeadline(newTodo.getDeadline());
          todo.setStatus(newTodo.getStatus());
          todo.setPriority(newTodo.isPriority());
          try{
            if(getDayDiff(todo.getDeadline()) > 0 && !todo.getStatus().equals("completed")){
                System.out.println(todo.getStatus());
                todo.setStatus("overdue");
            }} catch (DateTimeParseException e){
              todo.setDeadline("None");
          }
          return todoRepository.save(todo);
        }).orElseGet(()->{
            return todoRepository.save(newTodo);
        });

    }

    @PutMapping("/userprofiles/{id}/todos/{todoId}/{transferToId}")
    Todo transferTodo(@PathVariable Long id, @PathVariable Long todoId, @PathVariable Long transferToId){

        return todoRepository.findById(todoId).map(todo ->{
            todo.setUserId(transferToId);
            return todoRepository.save(todo);
        }).orElseGet(()->{
            throw new TodoNotFoundException(todoId);
        });

    }


    @DeleteMapping("/userprofiles/{id}/todos/{todoId}")

    void deleteTodo(@PathVariable Long id, @PathVariable Long todoId){
        todoRepository.deleteById(todoId);
    }

    @DeleteMapping("/userprofiles/{id}/todos/deleteall")
    void deleteAllTodos(@PathVariable Long id){
        List<Todo> list = allTodos(id);
        for(Todo todo : list ){
            todoRepository.deleteById(todo.getId());
        }
    }


    @PostMapping("/userprofiles")
    UserProfile newUserProfile(@RequestBody UserProfile newUserProfile){
        return repository.save(newUserProfile);
    }


    @PutMapping("/userprofiles/{id}")
    UserProfile replaceUserProfile(@RequestBody UserProfile newUserProfile, @PathVariable Long id){
        return repository.findById(id).map( userProfile -> {
            userProfile.setName((newUserProfile.getName()));
            userProfile.setPass((newUserProfile.getPass()));
            return repository.save(userProfile);
                }).orElseGet(() -> {
                   return repository.save(newUserProfile);
                });
    }

    @DeleteMapping("/userprofiles/{id}")
    void deleteUserProfile(@PathVariable Long id){
        repository.deleteById(id);
    }


    public long getDayDiff(String pdate) throws DateTimeParseException{
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.parse(pdate, format);
            return ChronoUnit.DAYS.between(date, LocalDate.now());
        } catch (DateTimeParseException e){
            throw new DateTimeParseException(null,pdate,e.getErrorIndex(),null);
        }
    }



}

