package bank.controllers;

import bank.db.entity.UserData;
import bank.db.entity.UserPersonal;
import bank.services.interfaces.AuthAndRegisterService;
import bank.services.interfaces.SecurityService;
import bank.services.interfaces.UserPanelService;
import bank.validator.UserValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("userData")
public class RegistrationController {

    @ModelAttribute("userData")
    public UserData createSessionUser(){
        return new UserData();
    }
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AuthAndRegisterService authAndRegisterService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserPanelService userPanelService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration (){

        return "registration";
    }


    @RequestMapping(value = "/registrattion_send", method = RequestMethod.POST)
    public ModelAndView sendRegistrationData(@RequestParam(value = "first_name") String first_name,
                                             @RequestParam(value = "second_name") String second_name,
                                             @RequestParam(value = "last_name") String last_name,
                                             @RequestParam(value = "login") String login,
                                             @RequestParam(value = "password") String password,
                                             @RequestParam(value = "data_reg") String data_reg,
                                             @RequestParam(value = "confirmPassword") String confirmPassword){
        logger.debug("ModelAndView registration() method is started...");
        UserPersonal userPersonal = new UserPersonal();
        userPersonal.setFirst_name(first_name);
        userPersonal.setSecond_name(second_name);
        userPersonal.setLast_name(last_name);
        UserData userData = new UserData();
        userData.setUser_personal(userPersonal);
        userData.setPassword(password);
        userData.setConfirmPassword(confirmPassword);
        userData.setLogin(login);
        userData.setDate_reg(data_reg);
        ModelAndView modelAndView = new ModelAndView("welcome");
            String loginError = userValidator.validateLogin(userData);
            String passError = userValidator.validatePassword(userData);
        if(loginError!=null){
            modelAndView.addObject("loginError",loginError);
            modelAndView.setViewName("registration");
            logger.info("login error");

        }else if(passError!=null){
            modelAndView.addObject("passError",passError);
            modelAndView.setViewName("registration");
            logger.info("pass Error");
        }else {
            authAndRegisterService.saveNewUser(userData, userPersonal, 0);
            userData = userPanelService.getUserByLoginAndPass(login,password);
            modelAndView.addObject("userData",userData);
            securityService.autoLogin(userData.getLogin(), userData.getPassword());

        }
        logger.debug("ModelAndView registration() method is uccessfully completed");
        return modelAndView;
    }
}
