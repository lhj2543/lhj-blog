package com.lhj.service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

import javax.wsdl.*;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author 17822
 */
public class WsService
{


   public List<Map<String, String>> getMethodFromWSDL(String url)
    {
        List retList = new ArrayList();

        if ((url != null) && (url.trim().length() > 0)) {
            try {
                WSDLFactory factory = WSDLFactory.newInstance();
                WSDLReader reader = factory.newWSDLReader();

                reader.setFeature("javax.wsdl.verbose", true);
                reader.setFeature("javax.wsdl.importDocuments", true);
                Definition def = reader.readWSDL(url);
                Map services = def.getServices();
                Iterator keys = services.keySet().iterator();
                Service service = (Service)services.get(keys.next());
                Map ports = service.getPorts();
                Iterator iter = ports.values().iterator();
                Port port = null;
                while (iter.hasNext()) {
                    port = (Port)iter.next();
                    Binding binding = port.getBinding();
                    PortType portType = binding.getPortType();
                    List operations = portType.getOperations();
                    Iterator operIter = operations.iterator();
                    while (operIter.hasNext()) {
                        Operation operation = (Operation)operIter.next();
                        if (!operation.isUndefined()) {
                            Map m = new HashMap();
                            m.put("name", operation.getName());
                            retList.add(m);
                        }
                    }
                }
            }
            catch (WSDLException e) {
                e.printStackTrace();
                System.out.println("获取ws接口方法异常");
            }

        }

        return retList;
    }
     /*
    public boolean reachable(String url)
    {
        int available = -1;
        if ((url != null) && (url.trim().length() > 0)) {
            InputStream is = null;
            try {
                if ((url.toLowerCase().startsWith("http://")) ||
                        (url
                                .toLowerCase().startsWith("https://"))) {
                    URL urlTmp = new URL(url);
                    is = urlTmp.openStream();
                    available = is.available();
                    is.close();
                } else {
                    is = new FileInputStream(url);
                    available = is.available();
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        is = null;
                    }
                }
            }
        }

        return available >= 0;
    }

    public Object getData(OutsideServerDto link) throws Exception {
        Object result = null;
        String url = link.getUrl();
        String method = link.getMethod();
        String params = link.getRequestMes();
        url = url.indexOf("?wsdl") <= 0 ? url + "?wsdl" : url;
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient(url);
        Object[] obj = client.invoke(method, new Object[] { params });
        result = obj[0];
        return result;
    }*/

    public static void main(String[] a) {

        try {
            String url3 = "http://61.143.33.101:8001/sap/bc/srt/wsdl/flv_10002A111AD1/bndg_url/sap/bc/srt/rfc/sap/zfm_spxgzt1fw/900/zfm_spxgzt1fw/zfm_spxgzt1fw?sap-client=900?wsdl";

            /*SI_PRCreate_osService service = new SI_PRCreate_osServiceLocator();
            SI_PRCreate_os port = service.getHTTP_Port();
            Stub stub = (Stub) port;
            String poname = PropUtil.getStringCK("setting", "ecology.poname");
            String popass = PropUtil.getStringCK("setting", "ecology.popass");
            stub.setUsername(poname);
            stub.setPassword(popass);*/

            WsService ws = new WsService();
            //List<Map<String, String>> methodFromWSDL = ws.getMethodFromWSDL(url3);
            //System.out.println(methodFromWSDL);

            JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();

            Client client = clientFactory.createClient(url3);

            Object[] obj = client.invoke("test1", new Object[] { "{}" });
            System.err.println(obj[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



}