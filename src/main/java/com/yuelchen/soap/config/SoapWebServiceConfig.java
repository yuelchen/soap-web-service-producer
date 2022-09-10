package com.yuelchen.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.servlet.ServletRegistration;

@Configuration
@EnableWs
public class SoapWebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> servletServletRegistrationBean(ApplicationContext context) {
        MessageDispatcherServlet dispatcherServlet = new MessageDispatcherServlet();
        dispatcherServlet.setApplicationContext(context);
        dispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(dispatcherServlet, "/ws/*");
    }

    @Bean(name = "dataset")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("Dataset");
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setTargetNamespace("http://www.yuelchen.com/soap/dataset");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema xsdSchema() {
        return new SimpleXsdSchema(new ClassPathResource("dataset.xsd"));
    }
}
