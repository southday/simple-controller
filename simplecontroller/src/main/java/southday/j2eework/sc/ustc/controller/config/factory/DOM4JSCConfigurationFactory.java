package southday.j2eework.sc.ustc.controller.config.factory;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import southday.j2eework.sc.ustc.controller.config.bean.Action;
import southday.j2eework.sc.ustc.controller.config.bean.Controller;
import southday.j2eework.sc.ustc.controller.config.bean.Interceptor;
import southday.j2eework.sc.ustc.controller.config.bean.InterceptorRef;
import southday.j2eework.sc.ustc.controller.config.bean.Result;
import southday.j2eework.sc.ustc.controller.config.bean.SCConfiguration;

/*
E3的controller.xml如下：
<sc-configuration>
    <interceptor name="log" class="southday.j2eework.water.ustc.interceptor.LogInterceptor"
        predo="preAction" afterdo="afterAction">
    </interceptor>
    <controller>
        <action name="login" class="southday.j2eework.water.ustc.action.LoginAction" method="handleLogin">
            <interceptor-ref name="log"></interceptor-ref>
            <result name="success" type="forward" value="/pages/welcome.jsp"></result> 
            <result name="faiulre" type="redirect" value="/pages/failure.jsp"></result>
        </action>
        <action name="register" class="southday.j2eework.water.ustc.action.RegisterAction" method="handleRegister">
            <interceptor-ref name="log"></interceptor-ref>
            <result name="success" type="forward" value="/pages/welcome.jsp"></result> 
        </action>
    </controller>
</sc-configuration>
 */

public class DOM4JSCConfigurationFactory implements SCConfigurationFactory {
    String controllerXMLPath = null;
    private static final SAXReader saxReader = new SAXReader();
    
    public DOM4JSCConfigurationFactory(String controllerXMLPath) {
        this.controllerXMLPath = controllerXMLPath;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public SCConfiguration create() throws Exception {
        Document doc = saxReader.read(controllerXMLPath);
        Element root = doc.getRootElement();
        SCConfiguration scc = new SCConfiguration();
        scc.setInterceptors(toInterceptorList(root.elements("interceptor")));
        scc.setControllers(toControllerList(root.elements("controller")));
        return scc;
    }
    
    private List<Interceptor> toInterceptorList(List<Element> elements) {
        List<Interceptor> interceptors = new ArrayList<>();
        for (Element e : elements) {
            Interceptor i = toInterceptor(e);
            if (i != null)
                interceptors.add(i);
        }
        return interceptors;
    }
    
    private List<Controller> toControllerList(List<Element> elements) {
        List<Controller> controllers = new ArrayList<>();
        for (Element e : elements) {
            Controller c = toController(e);
            if (c != null)
                controllers.add(c);
        }
        return controllers;
    }
    
    private Controller toController(Element element) {
        Controller controller = new Controller();
        List<Action> actions = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<Element> actionElements = element.elements("action");
        for (Element e : actionElements) {
            Action a = toAction(e);
            if (a != null)
                actions.add(a);
        }
        controller.setActions(actions);
        return controller;
    }
    
    private Interceptor toInterceptor(Element element) {
        Interceptor interceptor = new Interceptor();
        interceptor.setName(element.attributeValue("name"));
        interceptor.setClassName(element.attributeValue("class"));
        interceptor.setPredo(element.attributeValue("predo"));
        interceptor.setAfterdo(element.attributeValue("afterdo"));
        return interceptor;
    }
    
    private Action toAction(Element element) {
        Action action = new Action();
        action.setActionName(element.attributeValue("name"));
        action.setClassName(element.attributeValue("class"));
        action.setMethodName(element.attributeValue("method"));
        
        @SuppressWarnings("unchecked")
        List<Element> irefElements = element.elements("interceptor-ref");
        List<InterceptorRef> interceptorRefs = new ArrayList<>();
        for (Element e : irefElements) {
            InterceptorRef iref = toInterceptorRef(e);
            if (iref != null)
                interceptorRefs.add(iref);
        }
        action.setInterceptorRefs(interceptorRefs);
        
        @SuppressWarnings("unchecked")
        List<Element> resultElements = element.elements("result");
        List<Result> results = new ArrayList<>();
        for (Element e : resultElements) {
            Result r = toResult(e);
            if (r != null)
                results.add(r);
        }
        action.setResults(results);
        return action;
    }
    
    private InterceptorRef toInterceptorRef(Element element) {
        InterceptorRef interceptorRef = new InterceptorRef();
        interceptorRef.setName(element.attributeValue("name"));
        return interceptorRef;
    }
    
    private Result toResult(Element element) {
        Result result = new Result();
        result.setName(element.attributeValue("name"));
        result.setType(element.attributeValue("type"));
        result.setValue(element.attributeValue("value"));
        return result;
    }
}
