package com.acai.bions_api.config.security;

import com.acai.bions_api.Repositories.RoleRepository;
import com.acai.bions_api.enums.RoleName;
import com.acai.bions_api.models.RoleModel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.findByRolename(RoleName.ROLE_USER).isEmpty()) {
            roleRepository.save(new RoleModel(RoleName.ROLE_USER));
        }
        if (roleRepository.findByRolename(RoleName.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(new RoleModel(RoleName.ROLE_ADMIN));
        }
    }
}
