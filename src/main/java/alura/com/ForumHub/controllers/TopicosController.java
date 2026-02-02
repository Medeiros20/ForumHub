package alura.com.ForumHub.controllers;


import alura.com.ForumHub.domain.curso.CursoRepository;
import alura.com.ForumHub.domain.topico.DadosCadastroTopico;
import alura.com.ForumHub.domain.topico.DadosDetalhamentoTopico;
import alura.com.ForumHub.domain.topico.Topico;
import alura.com.ForumHub.domain.topico.TopicoRepository;
import alura.com.ForumHub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

//CONTROLER PARA REALIZAR AS CHAMADAS CRUD DOS TOPICOS

@RestController
@RequestMapping("topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastroTopico(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        // 1. Buscamos as referências dos objetos no banco
        var autor = usuarioRepository.getReferenceById(dados.idAutor());
        var curso = cursoRepository.getReferenceById(dados.idCurso());

        // 2. Criamos o tópico passando o DTO + os objetos encontrados
        // (Certifique-se que o construtor na classe Topico aceite esses 3 parâmetros)
        var topico = new Topico(dados, autor, curso);

        topicosRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }
}
