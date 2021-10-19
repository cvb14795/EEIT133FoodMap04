package cf.cvb14795.Coupon.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.web")
public class WebAppConfig implements WebMvcConfigurer {

	@Bean
    public  ViewResolver  internalResourceViewResolver() {
        InternalResourceViewResolver resolver = 
        			new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
		return resolver;
    }
	
	@Bean
	public MessageSource  messageSource() {
		ResourceBundleMessageSource  rbms = new ResourceBundleMessageSource();
		rbms.setBasename("messages");
		return rbms;
	}
	
	@Override 
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	     registry.addResourceHandler("/css/**")
	             .addResourceLocations("/WEB-INF/views/css/");
	     registry.addResourceHandler("/image/**")
	             .addResourceLocations("/WEB-INF/views/images/");
	} 
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(81920000);
		return resolver;
	}
}
