/*
package com.lhj.service;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;

*/
/**
 * @author 17822
 *//*

public class WsdlClientTest {

    public static void callWebservice() {
        try {
            // 指出service所在完整的URL
            String endpoint = "http://61.143.33.101:8001/sap/bc/srt/wsdl/flv_10002A111AD1/bndg_url/sap/bc/srt/rfc/sap/zfm_spxgzt1fw/900/zfm_spxgzt1fw/zfm_spxgzt1fw?sap-client=900?wsdl";
            //调用接口的targetNamespace
            String targetNamespace = "urn:sap-com:document:sap:rfc:functions";
            //所调用接口的方法method
            String method = "所要调用的方法名";
            // 创建一个服务(service)调用(call)
            Service service = new Service();

            // 通过service创建call对象
            Call call = (Call) service.createCall();
            // 设置service所在URL
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            call.setOperationName(new QName(targetNamespace, method));
            call.setUseSOAPAction(true);
            //变量最好只是用String类型，其他类型会报错
            //设置参数名 state  第二个参数表示String类型,第三个参数表示入参
            call.addParameter(new QName(targetNamespace, "变量名"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
            // 设置返回类型
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
            // String path = targetNamespace + method;
            // call.setSOAPActionURI(path);

            //此处为数组，有几个变量传几个变量
            String jsonString = (String) call.invoke(new Object[] {"变量值"});



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
*/
