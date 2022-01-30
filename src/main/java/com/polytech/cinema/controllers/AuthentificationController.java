package com.polytech.cinema.controllers;

import com.polytech.cinema.domains.UtilisateurEntity;
import com.polytech.cinema.validations.ValidAjoutUtilisateur;
import com.polytech.cinema.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polytech.cinema.services.JwtUserDetailsService;
import com.polytech.cinema.config.JwtTokenUtil;

import java.util.HashMap;
import java.util.Optional;

@RequestMapping("/authentification")
@RestController
@CrossOrigin
public class AuthentificationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public AuthentificationController(UtilisateurRepository UtilisateurRepository) {
        this.utilisateurRepository = UtilisateurRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UtilisateurEntity util) {
        Optional<UserDetails> userDetails = appelAuthentication(util.getNomUtil(), util.getMotPasse());
        Optional<UtilisateurEntity> _util = userDetailsService.getUtilisateurFromUsername(util.getNomUtil());
        if(userDetails.isPresent() && _util.isPresent()){
            final String token = jwtTokenUtil.generateToken(userDetails.get());

            // On renvoit le rôle et le token
            HashMap<String, String> json = new HashMap<String, String>();
            json.put("role", _util.get().getRole());
            json.put("token", token);

            return ResponseEntity.ok(json);
        }else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated({ValidAjoutUtilisateur.class}) @RequestBody UtilisateurEntity util){
        Optional<UtilisateurEntity> _util = userDetailsService.getUtilisateurFromUsername(util.getNomUtil());
        if(_util.isEmpty()){
            userDetailsService.register(util);

            return ResponseEntity.ok().build();
        }else
            return ResponseEntity.unprocessableEntity().build();
    }

    private Optional<UserDetails> appelAuthentication(String username, String password) {
        Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        return Optional.ofNullable(userDetails);
    }

}
