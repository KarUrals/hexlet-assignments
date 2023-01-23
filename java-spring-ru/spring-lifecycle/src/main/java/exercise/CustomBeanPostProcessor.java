// Класс для самостоятельной работы

package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
// Постпроцессор бинов
// Используется, если нужно кастомизировать работу с бинами
// Интерфейс содержит два метода
// Первый выполняется перед инициализацией бина, второй после

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    // Метод вызывается перед инициализацией бина
    // Принимает сам бин и имя бина
    // Должен вернуть бин
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("Called PostProcessBeforeInitialization: " + beanName);
        return bean;
    }

    @Override
    // Метод вызывается после инициализацией бина
    // Должен вернуть бин
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("Called PostProcessAfterInitialization: " + beanName);
        return bean;
    }
}
// END
