package org.example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.4.1
 * 2020-11-13T10:04:38.637+05:30
 * Generated source version: 3.4.1
 *
 */
@WebService(targetNamespace = "http://example.org/", name = "WsImpl")
@XmlSeeAlso({ObjectFactory.class})
public interface WsImpl {

    @WebMethod
    @RequestWrapper(localName = "test", targetNamespace = "http://example.org/", className = "org.example.Test")
    @ResponseWrapper(localName = "testResponse", targetNamespace = "http://example.org/", className = "org.example.TestResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String test(

        @WebParam(name = "arg0", targetNamespace = "")
        org.example.ThisObject arg0
    );
}
