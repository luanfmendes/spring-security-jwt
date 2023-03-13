package br.com.auth.jwt.resource;

import br.com.auth.jwt.model.LoginRequest;
import br.com.auth.jwt.model.User;
import br.com.auth.jwt.repository.UserRepository;
import br.com.auth.jwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {
    @Autowired
    UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/listar")
    public List<User> listar(){
        return repository.findAll();
    }
    @PostMapping("/create")
    public User create(@RequestBody User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return repository.save(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationTokennew =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationTokennew);

        var user = (User)authenticate.getPrincipal();

        return tokenService.gerarToken(user);
    }
}
