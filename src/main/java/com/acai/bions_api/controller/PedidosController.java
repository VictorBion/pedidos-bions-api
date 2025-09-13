package com.acai.bions_api.controller;

import com.acai.bions_api.Service.EmailSenderService;
import com.acai.bions_api.Service.PedidoService;
import com.acai.bions_api.dtos.EmailDto;
import com.acai.bions_api.dtos.PedidoComEmailDto;
import com.acai.bions_api.dtos.PedidoDto;
import com.acai.bions_api.dtos.UserDto;
import com.acai.bions_api.models.PedidoModel;
import com.acai.bions_api.models.RoleModel;
import com.acai.bions_api.models.UserModel;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/acai")
public class PedidosController {

   final PedidoService pedidoService;

    @Autowired
     EmailSenderService emailSenderService;

    public PedidosController(PedidoService pedidoService, PasswordEncoder passwordEncoder) {
        this.pedidoService = pedidoService;
    }


    @PostMapping("/salvarUsuario")
    public ResponseEntity<UserModel> salvarUsuario(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.salvarUsuario(userDto));
    }

    @PostMapping("/pedidos")
    public ResponseEntity<PedidoModel> postAcai(@Valid @RequestBody PedidoComEmailDto dto){
       return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.salvarPedidos(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PedidoModel>> getAcai(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable) {
        Page<PedidoModel> page = pedidoService.findAll(pageable);

        if (pageable.getPageNumber() >= page.getTotalPages() && page.isEmpty()) {
            throw new ResponseStatusException
                    (HttpStatus.BAD_REQUEST, "Página fora do intervalo. Total de páginas: " + page.getTotalPages()
            );
        }
        return ResponseEntity.ok(page);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAcai (@PathVariable(value = "id") UUID id){
        pedidoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("pedido criado com sucesso!");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atulizarPedido(@PathVariable(value = "id") UUID id, @RequestBody PedidoDto pedidoDto)   {
//        Optional<PedidoModel> pedidoModelOptional = pedidoService.findById(id);
//
//        if (!pedidoModelOptional.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
//        }
//        var pedidoModel = new PedidoModel();
//        pedidoModel.setId(pedidoModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.atualizarPedidos(id, pedidoDto));
    }
}
