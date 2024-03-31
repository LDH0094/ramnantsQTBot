package lee.ramnants.ramnants_qt_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RamnantsQtBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RamnantsQtBotApplication.class, args);
    }

}
