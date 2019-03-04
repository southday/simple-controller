package southday.j2eework.sc.ustc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import southday.j2eework.sc.ustc.controller.action.ActionIface;
import southday.j2eework.sc.ustc.controller.config.ControllerConfig;
import southday.j2eework.sc.ustc.controller.config.bean.Action;
import southday.j2eework.sc.ustc.controller.config.bean.Result;
import southday.j2eework.sc.ustc.controller.config.factory.ResultFactory;
import southday.j2eework.sc.ustc.controller.di.DIer;
import southday.j2eework.sc.ustc.controller.interceptor.ParameterInterceptor;
import southday.j2eework.sc.ustc.controller.proxy.ProxyFactory;
import southday.j2eework.sc.ustc.controller.transformer.XML2HTMLTransformer;
import southday.j2eework.sc.ustc.controller.util.ReflectUtil;

public class SimpleController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ControllerConfig cconfig = ControllerConfig.getControllerConfig();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Action action = getAction(req);
        Object proxy = newActionProxy(action);
        DIer.inject(proxy, action);
        ParameterInterceptor.fillUserParams(proxy, req.getParameterMap());
        Result result = execute(proxy, action);
        handleResult(result, req, resp);
    }
    
    private Action getAction(HttpServletRequest req) {
        String servletName = req.getServletPath();
        String actionName = servletName.substring(servletName.lastIndexOf("/")+1, servletName.lastIndexOf("."));
        return cconfig.getAction(actionName);
    }
    
    private Object newActionProxy(Action action) {
        try {
            Object target = ReflectUtil.newInstance(action.getClassName());
            Object proxy = ProxyFactory.newLogActionProxyInstance(target, action);
            return proxy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private Result execute(Object target, Action action) {
        String res = null;
        try {
            res = (String)ReflectUtil.execute(target, action.getMethodName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return action.getResult(res);
    }
    
    private void handleResult(Result result, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String value = result.getValue();
        if (value.lastIndexOf("_view.xml") != -1) {
            String xmlPath = realRootPath(req) + value;
            boolean success = XML2HTMLTransformer.transform(xmlPath);
            if (success)
                result.setValue(XML2HTMLTransformer.htmlPath(value));
            else
                result = ResultFactory.NO_REQ_RESOURCES;
        }
        if (ActionIface.FORWARED.equals(result.getType())) {
            req.getRequestDispatcher(result.getValue()).forward(req, resp);
        } else if (ActionIface.REDIRECT.equals(result.getType())) {
            resp.sendRedirect(req.getContextPath() + result.getValue());
        }
    }
    
    // localhost:8080/UseSC/ 项目在硬盘中的实际存储位置：xxx/UseSC/
    private String realRootPath(HttpServletRequest req) {
        return req.getSession().getServletContext().getRealPath("/");
    }
}
