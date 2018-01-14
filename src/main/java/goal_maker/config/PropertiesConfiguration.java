package goal_maker.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertiesConfiguration {

    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();

        propertyPlaceholderConfigurer.setIgnoreResourceNotFound(true);

        final Properties properties = new Properties();

        try {
            properties.load(PropertiesConfiguration.class.getClassLoader().getResourceAsStream("app.properties"));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }

        String appID = properties.getProperty("app.id");

        List<Resource> resources = new ArrayList() {{
            add(new FileSystemResource("/etc/GoalMaker/" + appID + "/app.properties"));
            add(new FileSystemResource("D:/GoalMaker/DBProperties" + appID + "/app.properties"));
        }};

        propertyPlaceholderConfigurer.setLocations(resources.toArray(new Resource[]{}));

        return propertyPlaceholderConfigurer;
    }
}
