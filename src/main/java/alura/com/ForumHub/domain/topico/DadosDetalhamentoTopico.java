package alura.com.ForumHub.domain.topico;

import alura.com.ForumHub.domain.topico.StatusTopico;
import alura.com.ForumHub.domain.topico.Topico;

import java.time.LocalDateTime;

//DTO, USADO PARA O DETALHAMENTO DO TOPICO

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        String autor,
        String curso
) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}