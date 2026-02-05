package alura.com.ForumHub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

//REPOSITORIO DA CLASS USUARIO (USUARIO = AUTOR)

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);

    //UserDetails findByNome(String nome);
}
