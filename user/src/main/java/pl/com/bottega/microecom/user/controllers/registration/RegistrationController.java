package pl.com.bottega.microecom.user.controllers.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.microecom.user.model.Role;
import pl.com.bottega.microecom.user.model.User;
import pl.com.bottega.microecom.user.model.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public void createAccount(@Valid @RequestBody CreateAccountRequest request) {
        if (userRepository.findByLogin(request.getLogin()) != null)
            throw new LoginOccupiedException();
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getLogin(), hashedPassword);
        user.addRole(Role.CUSTOMER);
        userRepository.save(user);
    }

}
