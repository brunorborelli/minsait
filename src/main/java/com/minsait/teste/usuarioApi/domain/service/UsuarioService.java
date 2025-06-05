package com.minsait.teste.usuarioApi.domain.service;

import com.minsait.teste.usuarioApi.api.dto.UsuarioInputPutDTO;
import com.minsait.teste.usuarioApi.api.dto.UsuarioInputPostDTO;
import com.minsait.teste.usuarioApi.api.dto.UsuarioOutputDTO;
import com.minsait.teste.usuarioApi.domain.exception.ObjectNotFoundException;
import com.minsait.teste.usuarioApi.domain.model.Usuario;
import com.minsait.teste.usuarioApi.domain.service.util.ValidationService;
import com.minsait.teste.usuarioApi.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ValidationService validationService;

    public Usuario buscarUsuario(Long usuarioId){
        Usuario usuario = usuarioRepository.buscarUsuarioAtivoPorId(usuarioId)
                .orElseThrow(()-> new ObjectNotFoundException("Usuario não encontrado com o id " +usuarioId+"."));
        return usuario;
    }

    public List<UsuarioOutputDTO> buscarUsuariosAtivos() {
        List<Usuario> usuariosAtivos = usuarioRepository.buscarUsuariosAtivos();
        return usuariosAtivos.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioOutputDTO.class))
                .collect(Collectors.toList());
    }

    public UsuarioOutputDTO cadastrarUsuario(UsuarioInputPostDTO input){
        validationService.isValidEmail(input.getEmail());
        Usuario usuario = modelMapper.map(input,Usuario.class);
        usuarioRepository.save(usuario);
        UsuarioOutputDTO output = modelMapper.map(usuario , UsuarioOutputDTO.class);
        return output;
    }

    @Transactional
    public UsuarioOutputDTO alterarUsuario(Long usuarioId, UsuarioInputPutDTO input) {
        validationService.isValidEmail(input.getEmail());
        Usuario usuario = buscarUsuario(usuarioId);
        modelMapper.map(input, usuario);
        usuarioRepository.save(usuario);
        UsuarioOutputDTO output = modelMapper.map(usuario, UsuarioOutputDTO.class);
        return output;
    }

    public void deletarUsuario(Long usuarioId){
        Usuario usuario = buscarUsuario(usuarioId);
        usuarioRepository.delete(usuario);
    }






}
