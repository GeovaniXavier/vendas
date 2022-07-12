package io.github.geovanix.domain.service.imp;

import io.github.geovanix.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(!username.equals("cicrano")){
            throw new UsernameNotFoundException("Usuário não encontrado na base de dados.");
        }

        return User
                .builder()
                .username("cicrano")
                .password(passwordEncoder.encode("123"))
                .roles("USER","ADMIN")
                .build();


    }
}
