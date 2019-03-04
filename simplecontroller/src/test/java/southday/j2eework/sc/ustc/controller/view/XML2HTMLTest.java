package southday.j2eework.sc.ustc.controller.view;

import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

public class XML2HTMLTest {
    
    public static void main(String[] args) throws Exception {
        try {
//            String xml = "F:/J2EE-SC/log/vxml.xml";
//            String xsl = "F:/J2EE-SC/log/vxsl.xsl";
//            String html = "F:/J2EE-SC/log/vhtml.html";
            String xml = "F:/J2EE-SC/view/success_view.xml";
            String xsl = "F:/J2EE-SC/view/success_view.xsl";
            String html = "F:/J2EE-SC/view/success_view.html";
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(
                    new javax.xml.transform.stream.StreamSource(xsl));
            FileOutputStream fop = new FileOutputStream(html);
            transformer.transform(
                    new javax.xml.transform.stream.StreamSource(xml),
                    new javax.xml.transform.stream.StreamResult(fop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
