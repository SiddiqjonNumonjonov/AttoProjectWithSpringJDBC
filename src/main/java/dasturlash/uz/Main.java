package dasturlash.uz;

import dasturlash.uz.configs.ApplicationConfig;
import dasturlash.uz.controllers.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MainController mainController = (MainController) applicationContext.getBean("mainController");
        mainController.start();

    }
}