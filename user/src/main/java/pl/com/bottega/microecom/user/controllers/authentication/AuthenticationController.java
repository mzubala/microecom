package pl.com.bottega.microecom.user.controllers.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.microecom.user.model.Authentication;
import pl.com.bottega.microecom.user.model.AuthenticationRepository;
import pl.com.bottega.microecom.user.model.User;
import pl.com.bottega.microecom.user.model.UserRepository;

@RestController
@RequestMapping("/authentications")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public AuthenticationResponse create(@RequestBody AuthenticationRequest request) {
        User user = userRepository.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user != null) {
            Authentication auth = user.createAuthentication();
            return AuthenticationResponse.success(auth.getToken());
        } else
            return AuthenticationResponse.failed();
    }

}
