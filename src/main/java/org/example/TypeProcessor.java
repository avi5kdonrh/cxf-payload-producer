package org.example;

import com.sun.xml.internal.ws.api.message.saaj.SAAJFactory;
import com.sun.xml.messaging.saaj.soap.ver1_1.HeaderElement1_1Impl;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.TypeConverter;
import org.apache.camel.component.cxf.CxfPayload;
import org.apache.cxf.binding.soap.SoapHeader;
import org.w3c.dom.Node;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

public class TypeProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        TypeConverter tc = exchange.getContext().getTypeConverter();
        MessageFactory saajFactory = SAAJFactory.getMessageFactory("SOAP 1.1 Protocol");
        SOAPMessage soapMessage = saajFactory.createMessage(null,new ByteArrayInputStream(exchange.getIn().getBody(String.class).getBytes()));
        Node node = null;
        Iterator<Node> text = soapMessage.getSOAPBody().getChildElements();
        while (text.hasNext()){
            Node tempNode = text.next();
            if (tempNode.getNodeType() == 1){
                node = tempNode;
                break;
            }
        }
        DOMSource source = new DOMSource();
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        source.setNode(node);
        transformer.transform(source, result);
        CxfPayload<SoapHeader> payload  =  tc.convertTo(CxfPayload.class,writer.toString());
        Iterator<HeaderElement1_1Impl> headers = soapMessage.getSOAPHeader().examineAllHeaderElements();
        while (headers.hasNext()){
            HeaderElement1_1Impl headerElement1_1 = headers.next();
            SoapHeader soapHeader = new SoapHeader(headerElement1_1.getElementQName(),headerElement1_1.getDomElement());
            payload.getHeaders().add(soapHeader);
        }
        System.out.println("The payload is created >>> "+payload);
        exchange.getIn().setBody(payload);
    }
}
