package work.inari.daily.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import work.inari.daily.spring.bean.Fruit;

@Configuration
public class ValueInjectConfig {

    private String fruitName;

    @Bean("configFruit")
    public Fruit fruit() {
        Fruit fruit = new Fruit();
        fruit.setName(this.fruitName);
        return fruit;
    }

    @Value("${fruit.name}")
    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }
}
