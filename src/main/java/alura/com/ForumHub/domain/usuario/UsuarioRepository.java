package alura.com.ForumHub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

//REPOSITORIO DA CLASS USUARIO (USUARIO = AUTOR)

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
