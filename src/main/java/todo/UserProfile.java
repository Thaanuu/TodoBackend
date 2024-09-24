package todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityModel;

@Entity
public class UserProfile {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String name;
    private String pass;
    //@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    //private List<Todo> todoList;

    UserProfile(){

    }


    UserProfile(String name, String pass, List<Todo> todoList) {
        this.name = name;
        this.pass = pass;
        //this.todoList = todoList;
    }
    UserProfile(String name, String pass) {
        this.name = name;
        this.pass = pass;
        //this.todoList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

   /* public List<Todo> getTodoList() {
        return todoList;
    }

    public void addTodoToList(Todo todo){

        this.todoList.add(todo);
    }

    public void setTodo(int i, Todo todo){
        this.todoList.set(i,todo);
    }

    public Todo getTodoById(Long id){

        for(Todo todo : todoList){
            if(todo.getId() == id){
                return  todo;
            }
        }
        throw new TodoNotFoundException(id);
    }


    public void deleteTodo(Long id){

        for(int i = 0; i < todoList.size(); i++){
            System.out.println(todoList);
            if(todoList.get(i).getId()== id){

                System.out.println(todoList);
                this.todoList.set(i, null);
                System.out.println(("h"));
                return;
            }
        }
        throw new TodoNotFoundException(id);
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(pass, that.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pass);
    }


    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

}


