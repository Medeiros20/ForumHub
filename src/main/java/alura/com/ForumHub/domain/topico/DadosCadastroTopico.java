package alura.com.ForumHub.domain.topico;

import alura.com.ForumHub.domain.curso.Curso;
import alura.com.ForumHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//DTO, USADO PARA DEFINIR QUAIS DADOS SÃO OBRIGATORIOS

public record DadosCadastroTopico(
        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        Long idAutor, // Mude de Usuario para Long

        @NotNull
        Long idCurso  // Mude de Curso para Long
) {
}