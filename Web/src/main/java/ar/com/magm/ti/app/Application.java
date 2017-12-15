package ar.com.magm.ti.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import ar.com.magm.ti.app.config.ContextConfig;
import ar.com.magm.ti.app.config.PersistenceConfig;
import ar.com.magm.ti.app.setup.MyApplicationContextInitializer;

/**
 * 
 * @author magm
 *
 */
@SpringBootApplication
@Import({ 
    ContextConfig.class,
    PersistenceConfig.class
}) 
public class Application extends SpringBootServletInitializer {
    

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).initializers(new MyApplicationContextInitializer()).run(args);
    }
    
}
