package xyz.vapr.repository.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private ZonedDateTime dueDate;

    public Todo(String title, String description, ZonedDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Todo() {

    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }
}
