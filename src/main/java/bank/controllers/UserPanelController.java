package bank.controllers;

import bank.db.entity.BankAccount;
import bank.db.entity.BankTransfer;
import bank.db.entity.RequestBankAccount;
import bank.db.entity.UserData;
import bank.services.interfaces.UserPanelService;
import bank.services.interfaces.UserShowInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Джамиль on 25.02.2018.
 */
@Controller
public class UserPanelController {

    @Autowired
    private UserPanelService userPanelService;
    @Autowired
    private UserShowInfoService userShowInfoService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView userPanel(@SessionAttribute("userData")UserData userData){
        logger.debug("ModelAndView userPanel() method is started...");
        ModelAndView modelAndView = new ModelAndView("user");

        List<BankAccount> bankAccountList = userShowInfoService.getListOfAllBankClients();
        modelAndView.addObject("bankAccountList",bankAccountList);

        List<BankTransfer> bankTransfers = userShowInfoService.getBankTransferByIdUser(userData.getId());
        modelAndView.addObject("bankTransfers",bankTransfers);

        RequestBankAccount requestBankAccount = userPanelService.getUserFromRequestBankAccountById(userData.getId());
        modelAndView.addObject("requestBankAccount", requestBankAccount);

        BankAccount bankAccount = userPanelService.getUserAccountById(userData.getId());
        modelAndView.addObject("userBankAccount", bankAccount);
        logger.debug("ModelAndView userPanel() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/newTransfer", method = RequestMethod.POST)
    public ModelAndView newTransfer(@RequestParam("id_payeeBankAccount") String id_payeeBankAccount,
                                    @RequestParam("sumT") String sum,
                                    @RequestParam("id_user_data")String id_user_data){
        logger.debug("ModelAndView newTransfer() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/user");

        BankAccount bankAccount = userPanelService.getUserAccountById(Integer.parseInt(id_user_data));
        int idSenderBankAccount = bankAccount.getId();
        String transfer = userPanelService.newTransfer(idSenderBankAccount,
                                                        Integer.parseInt(sum),
                                                        Integer.parseInt(id_payeeBankAccount),
                                                        Integer.parseInt(id_user_data));
        modelAndView.addObject("transfer",transfer);
        logger.debug("ModelAndView newTransfer() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/openBankAccount", method = RequestMethod.POST)
    public ModelAndView openBankAccount(@SessionAttribute("userData")UserData userData){
        logger.debug("ModelAndView openBankAccount() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/user");
        userPanelService.openAccount(userData.getId());
        logger.debug("ModelAndView openBankAccount() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/closeBankAccount", method = RequestMethod.POST)
    public ModelAndView closeBankAccount(@SessionAttribute("userData")UserData userData){
        logger.debug("ModelAndView closeBankAccount() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/user");
        BankAccount bankAccount = userPanelService.getUserAccountById(userData.getId());
        userPanelService.closeAccount(bankAccount.getId());
        logger.debug("ModelAndView closeBankAccount() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/putMoney", method = RequestMethod.POST)
    public ModelAndView putMoney(@SessionAttribute("userData")UserData userData,
                                 @RequestParam("sumP") String sum){
        logger.debug("ModelAndView putMoney() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/user");
        BankAccount bankAccount = userPanelService.getUserAccountById(userData.getId());
        String message = userPanelService.putMoney(Integer.parseInt(sum),bankAccount.getId());
        modelAndView.addObject("message",message);
        logger.debug("ModelAndView putMoney() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/getMoney", method = RequestMethod.POST)
    public ModelAndView getMoney(@SessionAttribute("userData")UserData userData,
                                 @RequestParam("sumG") String sum){
        logger.debug("ModelAndView getMoney() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/user");
        BankAccount bankAccount = userPanelService.getUserAccountById(userData.getId());
        String message = userPanelService.getMoney(Integer.parseInt(sum),bankAccount.getId());
        modelAndView.addObject("message1",message);
        logger.debug("ModelAndView getMoney() method is uccessfully completed");
        return modelAndView;
    }
}
