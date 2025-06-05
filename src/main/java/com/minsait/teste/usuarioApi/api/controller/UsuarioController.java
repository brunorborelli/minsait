package com.minsait.teste.usuarioApi.api.controller;

import com.minsait.teste.usuarioApi.api.dto.UsuarioInputPostDTO;
import com.minsait.teste.usuarioApi.api.dto.UsuarioInputPutDTO;
import com.minsait.teste.usuarioApi.api.dto.UsuarioOutputDTO;
import com.minsait.teste.usuarioApi.domain.model.Usuario;
import com.minsait.teste.usuarioApi.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuario(id);
            return ResponseEntity.ok(usuario);
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioOutputDTO>> getUsuariosAtivos() {
        List<UsuarioOutputDTO> usuariosAtivos = usuarioService.buscarUsuariosAtivos();
        return ResponseEntity.ok(usuariosAtivos);
    }

    @PostMapping()
    public ResponseEntity<UsuarioOutputDTO> cadastrarUsuario(@RequestBody UsuarioInputPostDTO input) {
            return new ResponseEntity<>(usuarioService.cadastrarUsuario(input), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioOutputDTO> alterarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioInputPutDTO input) {

        UsuarioOutputDTO usuarioOutput = usuarioService.alterarUsuario(id, input);
        return new ResponseEntity<>(usuarioOutput, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
