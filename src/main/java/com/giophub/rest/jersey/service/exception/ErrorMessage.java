package com.giophub.rest.jersey.service.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.beanutils.BeanUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.lang.reflect.InvocationTargetException;

// todo : find a way to create a tree xml node in the jaxb response like: {"Error":{"message":"The resource is not available.","code":"500"}}

@XmlRootElement
// define the output order of the elements
@XmlType(propOrder = {"severity", "code", "message"})
public class ErrorMessage {

    @Getter @Setter
    public String code;
    @Getter @Setter
    public String message;

    // If you like the variable name, e.g. "type", you can easily change this
    // name of the variable "severity" in "type" for your XML-Output:
    @XmlElement(name = "type")
    @Getter @Setter
    public String severity = "Error";



    /* Required to avoid boring errors like below:
        08-Mar-2019 15:19:44.249 SEVERE [http-nio-8080-exec-96] org.glassfish.jersey.message.internal.WriterInterceptorExecutor$TerminalWriterInterceptor.aroundWriteTo MessageBodyWriter not found for media type=application/json, type=class com.giophub.rest.jersey.service.exception.ErrorMessage, genericType=class com.giophub.rest.jersey.service.exception.ErrorMessage.
        08-Mar-2019 15:19:44.250 SEVERE [http-nio-8080-exec-96] org.glassfish.jersey.server.ServerRuntime$Responder.process Error occurred when processing a response created from an already mapped exception.
    */
    public ErrorMessage(){}

    ErrorMessage(JerseyServiceCustomException e) {
        try {
            BeanUtils.copyProperties(this, e);
        } catch (IllegalAccessException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }
}
