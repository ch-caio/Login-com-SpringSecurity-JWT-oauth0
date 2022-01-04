package projetocriptografar.criptografarsenha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projetocriptografar.criptografarsenha.model.UsuarioModel;
import projetocriptografar.criptografarsenha.repository.UsuarioRepositorty;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepositorty repositorty;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepositorty repositorty, PasswordEncoder encoder) {
        this.repositorty = repositorty;
        this.encoder = encoder;
    }

    @PostMapping("/salvar")
    private ResponseEntity<UsuarioModel> salvar (@RequestBody UsuarioModel usuarioModel){
        usuarioModel.setPassword(encoder.encode(usuarioModel.getPassword()));
        return ResponseEntity.ok(repositorty.save(usuarioModel));
    }

    @RequestMapping("/listar")
    private ResponseEntity<List<UsuarioModel>> listarTodos (){
        return  ResponseEntity.ok(repositorty.findAll());
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String password) {

        Optional<UsuarioModel> optUsuario = repositorty.findByLogin(login); // faz a pesquisa pelo login para verficiar se existe, caso nao ja retorna nao autorizado
        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        UsuarioModel usuario = optUsuario.get();
        boolean valid = encoder.matches(password, usuario.getPassword()); // o enconder verifica se esta certo a senha que esta vindo com o que esta no banco

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED; // se for valido retorna o OK
        return ResponseEntity.status(status).body(valid);
    }
}
