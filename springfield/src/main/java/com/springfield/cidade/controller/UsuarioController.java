package com.springfield.cidade.controller;

import com.springfield.cidade.cidadao.*;
import com.springfield.cidade.autenticacao.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final CidadaoRepository cidadaoRepository;

    public UsuarioController(UsuarioRepository usuarioRepository, CidadaoRepository cidadaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cidadaoRepository = cidadaoRepository;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioRequestDTO data) {
        Optional<Cidadao> cidadao = cidadaoRepository.findById(data.cidadaoId());

        if (cidadao.isEmpty()) {
            return ResponseEntity.badRequest().body("Cidadão não encontrado.");
        }

        if (usuarioRepository.findByUsername(data.username()).isPresent()) {
            return ResponseEntity.badRequest().body("Username já está em uso.");
        }

        Usuario usuario = new Usuario(null, cidadao.get(), data.username(), data.senha(), false, 0, LocalDateTime.now());
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO data) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(data.username());

        if (usuario.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }

        Usuario usuarioCidadao = usuario.get();

        if (usuarioCidadao.isBloqueado()) {
            return ResponseEntity.status(403).body("Usuário bloqueado! Entre em contato para desbloqueio.");
        }

        if (!usuarioCidadao.getSenha().equals(data.senha())) {
            usuarioCidadao.setTentativasLogin(usuarioCidadao.getTentativasLogin() + 1);
            
            if (usuarioCidadao.getTentativasLogin() >= 3) {
                usuarioCidadao.setBloqueado(true);
            }

            usuarioRepository.save(usuarioCidadao);
            return ResponseEntity.status(403).body("Senha incorreta! Tentativas restantes: " + (3 - usuarioCidadao.getTentativasLogin()));
        }

        usuarioCidadao.setTentativasLogin(0);
        usuarioCidadao.setUltimaTrocaSenha(LocalDateTime.now());
        usuarioRepository.save(usuarioCidadao);

        return ResponseEntity.ok("Login realizado com sucesso!");
    }

    @PostMapping("/troca-senha")
    public ResponseEntity<String> trocarSenha(@RequestBody TrocaSenhaDTO data) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(data.username());

        if (usuario.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }

        Usuario usuarioCidadao = usuario.get();

        if (!usuarioCidadao.getSenha().equals(data.senhaAtual())) {
            return ResponseEntity.status(403).body("Senha atual incorreta.");
        }

        usuarioCidadao.setSenha(data.novaSenha());
        usuarioCidadao.setUltimaTrocaSenha(LocalDateTime.now());
        usuarioRepository.save(usuarioCidadao);

        return ResponseEntity.ok("Senha alterada com sucesso!");
    }

    @PostMapping("/desbloqueio/{username}")
    public ResponseEntity<String> desbloquearUsuario(@PathVariable String username) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);

        if (usuario.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }

        Usuario usuarioCidadao = usuario.get();
        usuarioCidadao.setBloqueado(false);
        usuarioCidadao.setTentativasLogin(0);
        usuarioRepository.save(usuarioCidadao);

        return ResponseEntity.ok("Usuário desbloqueado com sucesso!");
    }
}
