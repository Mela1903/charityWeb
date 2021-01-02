package pl.coderslab.charity.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.MyUser;
import pl.coderslab.charity.repository.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    HttpSession session; //autowiring session

    @Autowired
    UserRepository repository; //autowire the admin repo


//    protected final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        MyUser user = new MyUser();
        String email = "";
        if (authentication.getPrincipal() instanceof Principal){
            email = ((Principal) authentication.getPrincipal()).getName();
            MyUser byEmail = repository.findByEmail(email);
            user = byEmail;
        } else {
            email = ((User)authentication.getPrincipal()).getUsername();
        }
//        logger.info("email: " + email);
        session.setAttribute("user", user);
    }
}
