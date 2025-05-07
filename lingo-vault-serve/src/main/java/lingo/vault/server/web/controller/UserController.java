package lingo.vault.server.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lingo.vault.server.service.AppUserService;
import lingo.vault.server.util.JwtUtils;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    AppUserService appUserService;
    
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {

        // Get username and pasword from client
        String username = request.get("username");
        String password = request.get("password");

        boolean isLegalUser = appUserService.login(username, password);
        if(isLegalUser) {
            String jwtToken = jwtUtils.generateToken(username);
            return new ResponseEntity<String>(jwtToken, HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));
        }
    }
}