package xyz.vapr.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.vapr.repository.TodoRepository;
import xyz.vapr.repository.entities.Todo;

import javax.inject.Inject;
import java.net.URI;

@Controller("/todo")
public class TodoController {

    private static final Logger LOG = LoggerFactory.getLogger(TodoController.class);

    @Inject
    private TodoRepository todoRepository;

    @Get("/{id}")
    public HttpResponse<Todo> getTodo(long id) {
        LOG.info("getTodo({})", id);
        return todoRepository.findById(id)
                .map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Post
    public HttpResponse<Todo> createTodo(@Body Todo todo) {
        Todo createdTodo = todoRepository.save(todo);
        return HttpResponse.created(URI.create("/todo/" + createdTodo.getId()));
    }

    @Put("/{id}")
    public HttpResponse<Todo> updateTodo(long id, @Body Todo todo) {
        return todoRepository.findById(id)
                .map(foundTodo -> HttpResponse.ok(todoRepository.save(todo)))
                .orElseGet(HttpResponse::notFound);
    }

    @Delete("/{id}")
    public HttpResponse<Todo> deleteTodo(long id) {
        return todoRepository.findById(id)
                .map(foundTodo -> {
                    todoRepository.delete(foundTodo);
                    return HttpResponse.<Todo>noContent();
                })
                .orElseGet(HttpResponse::notFound);
    }
}
