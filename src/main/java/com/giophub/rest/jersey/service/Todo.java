package com.giophub.rest.jersey.service;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
// Isn't that cool?
public class Todo {
    private static final Logger LOG = LoggerFactory.getLogger(Todo.class);

    @Getter @Setter
    private String summary;

    @Getter @Setter
    private String description;
}
