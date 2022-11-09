package tech.codingclub.helix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    private static  Logger logger = Logger.getLogger(String.valueOf(LoginController.class));

    @RequestMapping(method = RequestMethod.GET,value="/user")
    public String method(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request)
    {
        return "signin";
    }

    @RequestMapping(method = RequestMethod.GET,value="/admin")
    public String method1(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request)
    {
        return "hello";
    }

}
