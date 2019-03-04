package southday.j2eework.sc.ustc.controller.action;

public interface ActionIface {
    String SUCCESS = "success";
    String FAILURE = "failure";
    String FORWARED = "forward";
    String REDIRECT = "redirect";
    String execute() throws Exception;
}
