package alura.com.ForumHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//REPOSITORIO PARA A CLASS TOPICO

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Método para verificar se já existe um tópico com o mesmo título e mensagem
    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    @Query("""
            SELECT t FROM Topico t 
            WHERE t.curso.nome = :nome 
            AND YEAR(t.dataCriacao) = :ano
            """)
    Page<Topico> findAllByCursoNomeAndAno(String nomeCurso, Integer ano, Pageable paginacao);

//    @Query("""
//            SELECT t FROM Topico t
//            WHERE t.curso.nome = :nomeCurso
//            """)
    //Verifica se há nome do curso
    Page<Topico> findAllByCurso(String nomeCurso, Pageable paginacao);
}
