package southday.j2eework.water.ustc.log;

import java.io.File;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import southday.j2eework.water.ustc.log.bean.ActionLog;

public class LogWriter {
//    private static final String LOG_XML_PATH = "F:/J2EE-SC/log.xml";
    private static final String LOG_XML_PATH = LogProperties.getLogProperties().getValue("log.xml");
    private static final Document doc;
    private static final OutputFormat ofmt;
    
    static {
        File xmlFile = new File(LOG_XML_PATH);
        try {
            if (xmlFile.exists())
                doc = new SAXReader().read(xmlFile);
            else {
                doc = DocumentHelper.createDocument();
                doc.addElement("log");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // OutputFormat.createCompactFormat(); 是压缩版本的
        // OutputFormat.createPrettyPrint(); 是格式化版本的（可视）
        ofmt = OutputFormat.createPrettyPrint();
        ofmt.setEncoding("UTF-8");
    }
    
    public static void write(ActionLog actionLog) {
        new Thread(new Woker(actionLog)).start();
    }
    
    private static class Woker implements Runnable {
        private ActionLog actionLog;
        
        public Woker(ActionLog actionLog) {
            this.actionLog = actionLog;
        }

        @Override
        public void run() {
            addLog();
        }
        
        private void addLog() {
            Element root = doc.getRootElement();
            synchronized (LogWriter.class) {
                String threadName = "[" + actionLog.getActionName() + "-" + Thread.currentThread().getName() + "]";
                System.out.println(threadName + " start log...");
                addActionLogNode(root);
                writeLog();
                System.out.println(threadName + " end log.");
            }
        }
        
        private void addActionLogNode(Element root) {
            Element actionElement = root.addElement("action");
            Element name = actionElement.addElement("name");
            name.setText(actionLog.getActionName());
            Element startTime = actionElement.addElement("s-time");
            startTime.setText(actionLog.getStartTime());
            Element endTime = actionElement.addElement("e-time");
            endTime.setText(actionLog.getEndTime());
            Element result = actionElement.addElement("result");
            result.setText(actionLog.getResult());
        }
        
        private void writeLog() {
            XMLWriter writer = null;
            try {
                writer = new XMLWriter(new FileWriter(new File(LOG_XML_PATH)), ofmt);
                writer.write(doc);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (writer != null)
                        writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
