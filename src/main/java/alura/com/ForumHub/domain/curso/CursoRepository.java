package alura.com.ForumHub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;

//REPOSITORIO PARA A CLASSE CURSO
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
