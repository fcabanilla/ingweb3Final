package ar.com.magm.ti.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 
 * @author magm
 *
 */
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	//Configurar valores para archivos que serán subidos
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver r = new CommonsMultipartResolver();
		r.setMaxUploadSize(500000000);
		
		return r;
	}

}
