package com.bolsadeideas.springboot.app;

import java.nio.file.Paths;
import java.util.Locale;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer{


	private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// version 1
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
		log.info(resourcePath);
		registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);
	}
	
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/error_403").setViewName("error_403");
	}
	
	//Resuelve el locale, donde se va a almacenar el parametro de nuestro lenguaje, y se guarda en la sesion
	@Bean
	public LocaleResolver localeResolver() {
		
		SessionLocaleResolver localeResolver = new SessionLocaleResolver(); 
		
		localeResolver.setDefaultLocale(new Locale("es", "ES"));
		
		return localeResolver;
	}
	
	//Interceptor encargado de cambiar el lenguaje cada vez que se pasa el parametro lang
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor()
	{
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang"); 
		return localeInterceptor; 
	}
	
	//Para registrar el beans, apartir de aqui se configura el archivo properties
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	//Encargado de convertir/serializar el objeto a xml
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller()
	{
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(new Class[] {com.bolsadeideas.springboot.app.view.xml.ClienteList.class});
		return marshaller;
	}

	
}
