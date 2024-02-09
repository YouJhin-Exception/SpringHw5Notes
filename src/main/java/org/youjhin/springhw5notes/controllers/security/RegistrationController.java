package org.youjhin.springhw5notes.controllers.security;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.youjhin.springhw5notes.dto.UserRegistrationDto;
import org.youjhin.springhw5notes.enums.Role;
import org.youjhin.springhw5notes.model.User;
import org.youjhin.springhw5notes.repository.UserRepository;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") UserRegistrationDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            result.rejectValue("username", "user.username", "An account already exists with this username");
            return "register";
        }

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(Role.USER); // Default role
        userRepository.save(newUser);
        return "redirect:/login";
    }

}
