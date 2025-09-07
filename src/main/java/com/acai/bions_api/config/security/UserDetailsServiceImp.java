package com.acai.bions_api.config.security;

import com.acai.bions_api.Repositories.UserRepository;
import com.acai.bions_api.Service.PedidoService;
import com.acai.bions_api.models.UserModel;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {


    private UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Nome não encontrado" + username));
        System.out.println(userModel.getAuthorities());
        return new User(userModel.getUsername(), userModel.getPassword(), userModel.getAuthorities());
    }
}
