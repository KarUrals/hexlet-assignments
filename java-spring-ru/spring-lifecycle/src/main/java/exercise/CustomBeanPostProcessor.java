// Класс для самостоятельной работы

package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class CustomBeanPostProcessor {

    // Получаем из контекста
    @Autowired
    Daytime daytime;

    @Autowired
    Meal meal;

    @GetMapping("/daytime")
    public String root() {
        return "It is " + daytime.getName() +" now. Enjoy your " + meal.getMealForDaytime(daytime.getName());
    }
}
// END