package bank.services.implementations;

import bank.controllers.AdminPanelController;
import bank.db.dao.UserDataDAO;
import bank.db.entity.RolesData;
import bank.db.entity.UserData;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminPanelController.class);

    @Autowired
    private UserDataDAO userDataDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        logger.debug("method loadUserByUsername is started...");
        UserData userData = userDataDAO.findUserByLogin(login);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(RolesData rolesData : userData.getRoles_data()){
            grantedAuthorities.add(new SimpleGrantedAuthority(rolesData.getRole_name()));
        }
        logger.info("load user by login wherer login = "+login);
        return new org.springframework.security.core.userdetails.User(
                userData.getLogin(),
                userData.getPassword(),
                grantedAuthorities);

    }

}
