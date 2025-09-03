package com.acai.bions_api.Service;

import com.acai.bions_api.Repositories.PedidoRepository;
import com.acai.bions_api.Repositories.RoleRepository;
import com.acai.bions_api.Repositories.UserRepository;
import com.acai.bions_api.enums.RoleName;
import com.acai.bions_api.models.PedidoModel;
import com.acai.bions_api.models.RoleModel;
import com.acai.bions_api.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    PedidoRepository pedidoModelRepository;

    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public PedidoService(PedidoRepository pedidoModelRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.pedidoModelRepository = pedidoModelRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Transactional
    public PedidoModel save(PedidoModel pedidoModel){
        return pedidoModelRepository.save(pedidoModel);
    }


//    public List<PedidoModel> findAll(){
//        return pedidoModelRepository.findAll();
//    }

//    public UserModel salvarUsuario(UserModel user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//       return userRepository.save(user);
//    }

    public UserModel salvarUsuario(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepository.save(userModel);
    }

    public List<RoleModel> resolverRoles(List<String> rolesRecebidas) {
        if (rolesRecebidas == null || rolesRecebidas.isEmpty()) {
            return List.of(roleRepository.findByRolename(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Default role not found")));
        }

        return rolesRecebidas.stream()
                .map(roleStr -> roleRepository.findByRolename(RoleName.valueOf(roleStr))
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleStr)))
                .toList();
        }

    public Page<PedidoModel> findAll(Pageable pageable){
        return pedidoModelRepository.findAll(pageable);
    }


    public Optional<PedidoModel> findById(UUID id){
        return pedidoModelRepository.findById(id);
    }

    public void deleteById(UUID id) {
       pedidoModelRepository.deleteById(id);
    }
}
