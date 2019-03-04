package southday.j2eework.sc.ustc.controller.factory;

import com.alibaba.fastjson.JSONObject;

import southday.j2eework.sc.ustc.controller.config.bean.SCConfiguration;
import southday.j2eework.sc.ustc.controller.config.factory.DOM4JSCConfigurationFactory;

public class DOM4JSCConfigurationFactoryTest {

    public static void main(String[] args) throws Exception {
        String path = "src/test/java/southday/j2eework/sc/ustc/controller/factory/controller.xml";
        DOM4JSCConfigurationFactory o = new DOM4JSCConfigurationFactory(path);
        SCConfiguration scc = o.create();
        System.out.println(scc);
        
        System.out.println("----------------");
        String ss = "{\"controllers\":[{\"actions\":[{\"actionName\":\"login\",\"argList\":[],\"className\":\"southday.j2eework.water.ustc.action.LoginAction\",\"interceptorRefs\":[{\"name\":\"log\"}],\"methodName\":\"handleLogin\",\"results\":[{\"name\":\"success\",\"type\":\"forward\",\"value\":\"/pages/welcome.jsp\"},{\"name\":\"faiulre\",\"type\":\"redirect\",\"value\":\"/pages/failure.jsp\"}]},{\"actionName\":\"register\",\"argList\":[],\"className\":\"southday.j2eework.water.ustc.action.RegisterAction\",\"interceptorRefs\":[{\"name\":\"log\"}],\"methodName\":\"handleRegister\",\"results\":[{\"name\":\"success\",\"type\":\"forward\",\"value\":\"/pages/welcome.jsp\"}]}]}],\"interceptors\":[{\"afterdo\":\"afterAction\",\"className\":\"southday.j2eework.water.ustc.interceptor.LogInterceptor\",\"name\":\"log\",\"predo\":\"preAction\"}]}\r\n" + 
                "";
        System.out.println(JSONObject.toJSONString(ss));
    }
}
