package xyz.vapr;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceStartedEvent;
import io.micronaut.scheduling.annotation.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.vapr.repository.TodoRepository;
import xyz.vapr.repository.entities.Todo;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Singleton
@Requires(notEnv = Environment.TEST) // don't seed for tests
public class DatabaseSeeder implements ApplicationEventListener<ServiceStartedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseSeeder.class);

    @Inject
    private final TodoRepository todoRepository;

    public DatabaseSeeder(@NotNull final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Async
    @Override
    public void onApplicationEvent(final ServiceStartedEvent event) {
        LOG.info("Seeding data...");

        todoRepository.save(new Todo("first", "first", ZonedDateTime.now().plusDays(60)));
        todoRepository.save(new Todo("second", "second", ZonedDateTime.now().plusDays(1)));
    }
}
