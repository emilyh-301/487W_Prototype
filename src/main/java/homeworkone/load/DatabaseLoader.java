package homeworkone.load;

import homeworkone.repo.MemberDatabase;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Called when the application is initialized or refreshed.
 */
@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final MemberDatabase database;

    public DatabaseLoader(MemberDatabase database) {
        this.database = database;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
