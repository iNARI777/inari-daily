package work.inari.daily.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class TraditionalStringStarter {
    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
        Object fruit = beanFactory.getBean("fruit");
        Object loopA = beanFactory.getBean("loopB");
        System.out.println(fruit);
        System.out.println(loopA);
    }
}
