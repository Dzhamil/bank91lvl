package bank.controllers;

import bank.db.entity.UserData;
import bank.services.interfaces.CreateAdminAndTestUserService;
import bank.services.interfaces.SecurityService;
import bank.services.interfaces.UserPanelService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("userData")
public class AuthController {


    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserPanelService userPanelService;
    @Autowired
    private CreateAdminAndTestUserService createAdminAndTestUserService;

    public static String csrfForTest;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @ModelAttribute("userData")
    public UserData createSessionUser(){
        return new UserData();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        logger.debug("ModelAndView login() method is started...");
            createAdminAndTestUserService.createAdmin();
            createAdminAndTestUserService.createTestUsers();
        logger.debug("ModelAndView login() method is uccessfully completed");
        return new ModelAndView("login");

    }

    @RequestMapping(value = "/login_submit", method = RequestMethod.POST)
    public ModelAndView loginSubmit(@RequestParam(value = "login") String login,
                                    @RequestParam(value = "password") String password,
                                    @ModelAttribute("userData") UserData userData){
        logger.debug("ModelAndView loginSubmit() method is started...");
        securityService.autoLogin(login,password);
        ModelAndView modelAndView =  new ModelAndView("welcome");
        userData = userPanelService.getUserByLoginAndPass(login,password);
        modelAndView.addObject("userData",userData);
        logger.debug("ModelAndView loginSubmit() method is uccessfully completed");
        return modelAndView;
    }
}
