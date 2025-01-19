package forumHub.api.controller;

import forumHub.api.domain.topico.*;
import forumHub.api.repository.TopicoRepository;
import forumHub.api.repository.UsuarioRepository;
import forumHub.api.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoService topicoService;


    @GetMapping("/{id}")
    public ResponseEntity getTopicoById(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhesTopicos(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhesTopicos>> getTopicos(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        var page = topicoRepository.findAll(paginacao).map(DadosDetalhesTopicos::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity postTopico(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        try {
            var topico = topicoService.postTopico(dados);
            var uri = uriBuilder.path("/topico/{id}").buildAndExpand(topico).toUri();
            return ResponseEntity.created(uri).body((topico));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/msg/{id}")
    @Transactional
    public ResponseEntity putMsgTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopicoMensagem dados) {
        try {
            var topico = topicoRepository.getReferenceById(id);
            topico.atualizarTopicosMensagem(dados);

            return ResponseEntity.ok(new DadosDetalhesTopicos(topico));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/{id}")
    @Transactional
    public ResponseEntity putStatusTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopicoStatus dados) {
        try {
            var topico = topicoRepository.getReferenceById(id);

            topico.atualizarTopicosStatus(dados);

            return ResponseEntity.ok(new DadosDetalhesTopicos(topico));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopico(@PathVariable Long id){
        try {
            topicoRepository.deleteById(id);

            return ResponseEntity.ok("Topico deletado com sucesso!");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}