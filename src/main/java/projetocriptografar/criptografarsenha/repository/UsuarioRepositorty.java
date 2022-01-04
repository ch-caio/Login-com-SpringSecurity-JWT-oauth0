package projetocriptografar.criptografarsenha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetocriptografar.criptografarsenha.model.UsuarioModel;

import java.util.Optional;

public interface UsuarioRepositorty extends JpaRepository <UsuarioModel, Integer> {

    public Optional<UsuarioModel> findByLogin(String login);
}
