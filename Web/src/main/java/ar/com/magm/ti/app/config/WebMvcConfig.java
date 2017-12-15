package ar.com.magm.ti.app.config;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 
 * @author magm
 *
 */
public class WebMvcConfig extends WebMvcConfigurerAdapter {
/*
	//Configurar valores para archivos que serán subidos
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver r = new CommonsMultipartResolver();
		r.setMaxUploadSize(900000000);
		
		return r;
	}
*/
}
