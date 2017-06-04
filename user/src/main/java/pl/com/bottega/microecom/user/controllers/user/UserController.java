package pl.com.bottega.microecom.user.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.com.bottega.microecom.user.model.User;
import pl.com.bottega.microecom.user.model.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/current")
    public UserInfo current() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return new UserInfo(user);
    }

    @PutMapping("/{id}/roles")
    @Transactional
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateUserRolesRequest updateUserRequest) {
        User user = repository.findOne(id);
        user.updateRoles(updateUserRequest.getRoles());
    }

}
