package projetocriptografar.criptografarsenha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EncriptarSenhaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncriptarSenhaApplication.class, args);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        return enconder;
    }
}
