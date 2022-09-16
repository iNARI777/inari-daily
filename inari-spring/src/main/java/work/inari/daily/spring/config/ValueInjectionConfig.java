package work.inari.daily.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.inari.daily.spring.bean.Fruit;

@Configuration
public class ValueInjectionConfig {
    @Value("${my.fruit}")
    private String fruitName;

    @Bean
    public Fruit fruit() {
        Fruit fruit = new Fruit();
        fruit.setName(fruitName);
        return fruit;
    }
}
