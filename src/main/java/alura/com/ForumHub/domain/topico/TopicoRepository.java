package alura.com.ForumHub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

//REPOSITORIO PARA A CLASS TOPICO

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Método para verificar se já existe um tópico com o mesmo título e mensagem
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}
