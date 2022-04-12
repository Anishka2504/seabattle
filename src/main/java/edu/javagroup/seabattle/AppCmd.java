package edu.javagroup.seabattle;

import edu.javagroup.seabattle.init.Initializer;
import org.springframework.boot.CommandLineRunner;

/**
 * @author kaa
 * @version 1.0
 */
public class AppCmd implements CommandLineRunner {

    @Override
    public void run(String... args) {
        new Initializer().init();
    }
}
