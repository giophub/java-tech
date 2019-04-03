package com.giophub.soap.jaxws.service.contract;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface SoapService {

    @WebMethod String HelloWorld();
    @WebMethod String sayHello(@WebParam(name = "name") String name);
}
