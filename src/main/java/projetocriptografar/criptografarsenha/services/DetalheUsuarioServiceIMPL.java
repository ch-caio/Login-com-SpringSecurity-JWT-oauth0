package projetocriptografar.criptografarsenha.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import projetocriptografar.criptografarsenha.data.DetalheUsuarioData;
import projetocriptografar.criptografarsenha.model.UsuarioModel;
import projetocriptografar.criptografarsenha.repository.UsuarioRepositorty;

import java.util.Optional;

public class DetalheUsuarioServiceIMPL implements UserDetailsService {

    private final UsuarioRepositorty repository;

    public DetalheUsuarioServiceIMPL(UsuarioRepositorty repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario = repository.findByLogin(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario ["+ username +"] nao encontrado");
        }
        return new DetalheUsuarioData(usuario);
    }
}
