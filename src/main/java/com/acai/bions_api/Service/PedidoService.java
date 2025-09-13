package com.acai.bions_api.Service;

import com.acai.bions_api.Repositories.PedidoRepository;
import com.acai.bions_api.Repositories.RoleRepository;
import com.acai.bions_api.Repositories.UserRepository;
import com.acai.bions_api.dtos.EmailDto;
import com.acai.bions_api.dtos.PedidoComEmailDto;
import com.acai.bions_api.dtos.PedidoDto;
import com.acai.bions_api.dtos.UserDto;
import com.acai.bions_api.enums.RoleName;
import com.acai.bions_api.models.PedidoModel;
import com.acai.bions_api.models.RoleModel;
import com.acai.bions_api.models.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {

    final PedidoRepository pedidoModelRepository;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final EmailSenderService emailSenderService;
    private PasswordEncoder passwordEncoder;

    public PedidoService(PedidoRepository pedidoModelRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
        this.roleRepository = roleRepository;
        this.pedidoModelRepository = pedidoModelRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public PedidoModel salvarPedidos(PedidoComEmailDto dto){
        PedidoModel pedidoModel = new PedidoModel();
        BeanUtils.copyProperties(dto.pedidoDto(), pedidoModel);
        EmailDto emailDto = dto.emailDto();
        emailSenderService.sendEmail(emailDto.to(), emailDto.subject(), emailDto.body());
        return pedidoModelRepository.save(pedidoModel);
    }

    @Transactional
    public PedidoModel atualizarPedidos(UUID id, PedidoDto pedidoDto) {
        PedidoModel pedidoExistente = pedidoModelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));

        // Copia os dados do DTO para o model, ignorando o campo "id"
        BeanUtils.copyProperties(pedidoDto, pedidoExistente, "id");

        return pedidoModelRepository.save(pedidoExistente);
    }

    public UserModel salvarUsuario(UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel.setPassword(passwordEncoder.encode(userDto.password()));
        BeanUtils.copyProperties(userDto, userModel);

        List<RoleModel> rolesConvertidas = resolverRoles(userDto.roles());
        userModel.setRoles(rolesConvertidas);
        return userRepository.save(userModel);
    }
    public void deleteById(UUID id) {
        Optional<PedidoModel> pedidoModelOptional = findById(id);
        if(pedidoModelOptional.isEmpty()){
            throw new RuntimeException("Pedido não encontrado!");
        }
        pedidoModelRepository.deleteById(id);
    }

    public List<RoleModel> resolverRoles(List<String> rolesRecebidas) {
        if (rolesRecebidas == null || rolesRecebidas.isEmpty()) {
            return List.of(roleRepository.findByRolename(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role não encontrada")));
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

}


//classe anonima
//.orElseThrow(new Supplier<RuntimeException>() {
//    @Override
//    public RuntimeException get() {
//        return new RuntimeException("Default role not found");
//    }
//});
