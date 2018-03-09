package bank.controllers;

import bank.db.entity.BankAccount;
import bank.db.entity.BankTransfer;
import bank.db.entity.RequestBankAccount;
import bank.services.interfaces.AdminPanelService;
import bank.services.interfaces.AdminShowInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminPanelController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private AdminShowInfoService adminShowInfoService;
    @Autowired
    private AdminPanelService adminPanelService;


    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ModelAndView admin(){
        logger.debug("ModelAndView admin() method is started...");
        ModelAndView modelAndView = new ModelAndView("admin");

        logger.info("load all account in admin page...");
        List<BankAccount> bankAccountList = adminShowInfoService.getAllAccount();
        logger.info("load all account in admin page is completed");
        modelAndView.addObject("bankAccountList",bankAccountList);

        logger.info("load all request bank account in admin page...");
        List<RequestBankAccount> requestBankAccountList = adminShowInfoService.getAllRequestToBankAccount();
        logger.info("load all request bank account  in admin page is completed");
        modelAndView.addObject("requestBankAccountList",requestBankAccountList);

        logger.info("load bank transfer in admin page...");
        List<BankTransfer> bankTransferList = adminShowInfoService.getAllBankTransfer();
        logger.info("load bank transfer  in admin page is completed");
        modelAndView.addObject("bankTransferList",bankTransferList);

        logger.info("load bank transfer where status is false in admin page...");
        List<BankAccount> bankTransferListFalse = adminShowInfoService.getBankTransferWhereStatusIsFalse();
        logger.info("load bank transfer where  in admin page is completed");
        modelAndView.addObject("bankTransferListFalse",bankTransferListFalse);

        logger.debug("ModelAndView admin() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/acceptOpenAccount", method = RequestMethod.POST)
    public ModelAndView acceptOpenAccount(@RequestParam("id_user_data") String id_user_data,
                                          @RequestParam("id_requestBankAccount") String id_requestBankAccount) {
        logger.debug("ModelAndView acceptOpenAccount() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/admin");
        String messageOk = adminPanelService.acceptOpenAccount(Integer.parseInt(id_user_data),
                                            Integer.parseInt(id_requestBankAccount));
        modelAndView.addObject("messageOk",messageOk);
        logger.info("admin accept open account");
        logger.debug("ModelAndView acceptOpenAccount() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/cancelOpenAccount", method = RequestMethod.POST)
    public ModelAndView cancelOpenAccount(@RequestParam("id_requestBankAccount") String id_requestBankAccount) {
        logger.debug("ModelAndView cancelOpenAccount() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/admin");
        String messageCancel = adminPanelService.cancelOpenAccount(Integer.parseInt(id_requestBankAccount));
        modelAndView.addObject("messageCancel",messageCancel);
        logger.info("admin don't accept open account");
        logger.debug("ModelAndView cancelOpenAccount() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/acceptTransfer",method = RequestMethod.POST)
    public ModelAndView acceptTransfer(@RequestParam("sum") String sum,
                                       @RequestParam("id_payeeBankAccount")String id_payeeBankAccount,
                                       @RequestParam("id_bankTransfer")String id_bankTransfer){
        logger.debug("ModelAndView acceptTransfer() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/admin");
        String message = adminPanelService.acceptTransfer(
                                        Integer.parseInt(sum),
                                        Integer.parseInt(id_payeeBankAccount),
                                        Integer.parseInt(id_bankTransfer));
        modelAndView.addObject("message",message);
        logger.info("admin accept the transfer for bank transfer id  ="+ id_bankTransfer);
        logger.debug("ModelAndView admin() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/cancelTrasfer",method = RequestMethod.POST)
    public ModelAndView cancelTrasfer (@RequestParam("sum") String sum,
                                       @RequestParam("id_bankTransfer")String id_bankTransfer){
        logger.debug("ModelAndView cancelTrasfer() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/admin");
        String message1 = adminPanelService.cancelTrasfer(
                Integer.parseInt(sum),
                Integer.parseInt(id_bankTransfer));
        modelAndView.addObject("message1",message1);
        logger.info("admin cancel transfer for transfer id ="+ id_bankTransfer );
        logger.debug("ModelAndView cancelTrasfer() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/acceptCloseAccount",method = RequestMethod.POST)
    public ModelAndView acceptCloseAccount (@RequestParam("id_bank_account") String id_bank_account,
                                            @RequestParam("id_user_data") String id_user_data){
        logger.debug("ModelAndView acceptCloseAccount() method is started...");
        ModelAndView modelAndView = new ModelAndView("forward:/admin");
        String acceptClose = adminPanelService.acceptCloseAccount(Integer.parseInt(id_bank_account),
                                                                    Integer.parseInt(id_user_data));
        modelAndView.addObject("acceptClose", acceptClose);
        logger.info("admin accept Close Account for user id = "+id_user_data);
        logger.debug("ModelAndView acceptCloseAccount() method is uccessfully completed");
        return modelAndView;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String start(){
        return "login";
    }
}
