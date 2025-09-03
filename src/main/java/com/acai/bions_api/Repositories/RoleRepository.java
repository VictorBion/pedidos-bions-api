package com.acai.bions_api.Repositories;

import com.acai.bions_api.enums.RoleName;
import com.acai.bions_api.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

    public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

        Optional<RoleModel> findByRolename(RoleName rolename);

    }


