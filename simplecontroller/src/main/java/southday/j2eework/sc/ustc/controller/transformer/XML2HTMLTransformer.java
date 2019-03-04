package southday.j2eework.sc.ustc.controller.transformer;

import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import southday.j2eework.sc.ustc.controller.util.CommonUtil;

public class XML2HTMLTransformer {
    private static TransformerFactory tFactory = TransformerFactory.newInstance();

    public static boolean transform(String xml) {
        if (!CommonUtil.checkParam(xml))
            return false;
        String xsl = xslPath(xml);
        String html = htmlPath(xml); 
        try {
            Transformer transformer = tFactory.newTransformer(new StreamSource(xsl));
            transformer.transform(new StreamSource(xml), new StreamResult(new FileOutputStream(html)));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static String xslPath(String xml) {
        if (!CommonUtil.checkParam(xml))
            return null;
        return xml.replace(".xml", ".xsl");
    }
    
    public static String htmlPath(String xml) {
        if (!CommonUtil.checkParam(xml))
            return null;
        return xml.replace(".xml", ".html");
    }
}