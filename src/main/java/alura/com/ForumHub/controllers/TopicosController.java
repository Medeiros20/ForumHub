package alura.com.ForumHub.controllers;


import alura.com.ForumHub.domain.curso.CursoRepository;
import alura.com.ForumHub.domain.topico.*;
import alura.com.ForumHub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopico(
            //Essa opção captura valores da URL, como esá como false a API funciona mesmo sem os parametros
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer ano,
            // Configura a paginação padrão que é 10 itens por página, ordenados pelo mais antigos primeiro).
            @PageableDefault(size = 10, sort = "dataCriacao", direction = org.springframework.data.domain.Sort.Direction.ASC) Pageable paginacao){

        Page<Topico> pagina;
        //CRITERIOS DE BUSCA
        //Logica para caso o nome do curso e o ano seja diferente de nulo, a busca no banco de dados será pelo nome do curso e ano
        if (curso != null && ano != null) {
            pagina = topicosRepository.findAllByCursoNomeAndAno(curso, ano, paginacao);
        }
        //Caso apenas o nome do curso seja diferente de do null, a busca no banco de dados será apenas pelo nome do curso
        else if (curso != null) {
            pagina = topicosRepository.findAllByCurso(curso, paginacao);
        }
        //Caso nenhum filtro ou apenas o ano for enviado, a busca no banco de dados será geral
        else{
            pagina = topicosRepository.findAll(paginacao);
        }

        return ResponseEntity.ok(pagina.map(DadosListagemTopico::new));
    }
}
