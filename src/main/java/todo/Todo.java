package todo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Todo {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;



    private Long userId;
    private String todoText;
    private String description;
    private String deadline;
    private String status;
    private boolean priority;




    public Todo(){}
    public Todo( Long userId, String todoText, String description, String deadline) {
        this.userId = userId;
        this.todoText = todoText;
        this.description = description;
        this.deadline = deadline;
        this.status = "open";
        this.priority = false;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTodoText() {
        return todoText;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return priority == todo.priority && Objects.equals(id, todo.id) && Objects.equals(userId, todo.userId) && Objects.equals(todoText, todo.todoText) && Objects.equals(description, todo.description) && Objects.equals(deadline, todo.deadline) && Objects.equals(status, todo.status);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", userId=" + userId +
                ", todoText='" + todoText + '\'' +
                ", description='" + description + '\'' +
                ", deadline='" + deadline + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, todoText, description, deadline, status, priority);
    }
}
