package forumHub.api.controller;


import forumHub.api.domain.resposta.DadosAtualizacaoResposta;
import forumHub.api.domain.resposta.DadosCadastroResposta;
import forumHub.api.domain.resposta.DadosDetalhesResposta;
import forumHub.api.domain.topico.DadosAtualizacaoTopicoMensagem;
import forumHub.api.domain.topico.DadosCadastroTopico;
import forumHub.api.domain.topico.DadosDetalhesTopicos;
import forumHub.api.repository.RespostaRepository;
import forumHub.api.service.RespostaService;
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
@RequestMapping("/resposta")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private RespostaService respostaService;

    @GetMapping("/{id}")
    public ResponseEntity getRespostaById(@PathVariable Long id) {
        var resposta = respostaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhesResposta(resposta));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhesResposta>> getResposta(@PageableDefault(size = 10, sort = {"mensagem"}) Pageable paginacao) {
        var page = respostaRepository.findAll(paginacao).map(DadosDetalhesResposta::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity postResposta(@RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriBuilder) {
        try {
            var resposta = respostaService.postResposta(dados);
            var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(resposta).toUri();
            return ResponseEntity.created(uri).body((resposta));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity putResposta(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoResposta dados) {
        try {
            var resposta = respostaRepository.getReferenceById(id);
            resposta.atualizarInformacoesResposta(dados);

            return ResponseEntity.ok(new DadosDetalhesResposta(resposta));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteResposta(@PathVariable Long id){
        try {
            respostaRepository.deleteById(id);

            return ResponseEntity.ok("Resposta deletada com sucesso!");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}



