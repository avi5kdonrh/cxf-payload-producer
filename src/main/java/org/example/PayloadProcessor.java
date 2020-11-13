package org.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PayloadProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
       /* String id = "1234";
        String desc = "value1";
       String payload = "<ns1:test xmlns:ns1=\"http://example.org/\">" +
                         "<arg0>" +
                      "<desc>"+desc+"</desc>" +
                      "<id>"+id+"</id>" +
                     "</arg0>" +
                   "</ns1:test>";
       CxfPayload<SoapHeader> cxfPayload = CxfPayloadConverter.documentToCxfPayload(new XmlConverter().toDOMDocument(payload,exchange),exchange);
       exchange.getIn().setBody(cxfPayload);

        SoapHeader soapHeader = new SoapHeader(new QName("http://example.org/", "ContractVersion"),"clced",new JAXBDataBinding(String.class));
        cxfPayload.getHeaders().add(soapHeader);*/

        String payload = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:exam=\"http://example.org/\" xmlns:web=\"http://example.org/\">" +
                "    <soapenv:Header>" +
                "      <web:ContractVersion>0</web:ContractVersion>" +
                "      <web:NewTest>A Test</web:NewTest>" +
                "   </soapenv:Header>" +
                "   <soapenv:Body>" +
                "      <exam:test>" +
                "      <arg0>" +
                "            <desc>ffdf</desc>" +
                "            <id>445</id>" +
                "           </arg0>" +
                "      </exam:test>" +
                "   </soapenv:Body>" +
                "</soapenv:Envelope>";
     //   CxfPayload<SoapHeader> cxfPayload = CxfPayloadConverter.documentToCxfPayload(new XmlConverter().toDOMDocument(payload,exchange),exchange);
        exchange.getIn().setBody(payload);
    }
}
