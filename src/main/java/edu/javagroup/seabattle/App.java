package edu.javagroup.seabattle;

// убрать комментарий после выполнения задач и тестов
import edu.javagroup.seabattle.frame.MainFrame;
import org.springframework.boot.CommandLineRunner;
// закомментировать после выполнения задач и тестов
//import org.springframework.boot.SpringApplication;
// строку ниже не трогать
import org.springframework.boot.autoconfigure.SpringBootApplication;
// убрать комментарий после выполнения задач и тестов
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author kaa
 * @version 1.0
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // закомментировать после выполнения задач и тестов
//        SpringApplication.run(App.class, args);
        // убрать комментарий после выполенения задач и тестов
        ConfigurableApplicationContext context = new SpringApplicationBuilder(App.class).headless(false).run(args);
        MainFrame mainFrame = context.getBean(MainFrame.class);
        mainFrame.setVisible(true);
    }

    // убрать комментарий после выполнения задач и тестов
    @Bean
    public CommandLineRunner commandLineRunner() {
        return new AppCmd();
    }
}
