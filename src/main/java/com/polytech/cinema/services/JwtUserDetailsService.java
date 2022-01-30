package com.polytech.cinema.services;

import java.util.ArrayList;
import java.util.Optional;

import com.polytech.cinema.config.JwtTokenUtil;
import com.polytech.cinema.domains.UtilisateurEntity;
import com.polytech.cinema.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;
    private JwtTokenUtil jwtTokenUtil;

    // on initialise
    @Autowired
    public JwtUserDetailsService(UtilisateurRepository UtilisateurRepository, JwtTokenUtil JwtTokenUtil) {
        this.utilisateurRepository = UtilisateurRepository;
        this.jwtTokenUtil = JwtTokenUtil;
    }

    public String getRoleFromToken(String token) {
        String username = this.jwtTokenUtil.getUsernameFromToken(token);
        return utilisateurRepository.rechercheNom(username).getRole();
    }

    public Optional<UtilisateurEntity> getUtilisateurFromUsername(String username) {
        UtilisateurEntity unUtilisateur = utilisateurRepository.rechercheNom(username);
        return Optional.ofNullable(unUtilisateur);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurEntity unUtilisateur = null;
        unUtilisateur = utilisateurRepository.rechercheNom(username);
        if (unUtilisateur != null) {
            return new User(unUtilisateur.getNomUtil(), unUtilisateur.getMotPasse(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public boolean checkTokenRole(String BearerToken, String role) {
        String token = BearerToken.split(" ")[1];
        String userRole = getRoleFromToken(token);
        return role.equals(userRole);
    }

    public void register(UtilisateurEntity util) {
        util.setRole("member");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = encoder.encode(util.getMotPasse());
        util.setMotPasse(pass);
        utilisateurRepository.save(util);
    }
}