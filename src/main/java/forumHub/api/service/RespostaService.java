package forumHub.api.service;


import forumHub.api.domain.resposta.DadosCadastroResposta;
import forumHub.api.domain.resposta.DadosDetalhesResposta;
import forumHub.api.domain.resposta.Resposta;
import forumHub.api.domain.topico.DadosCadastroTopico;
import forumHub.api.domain.topico.DadosDetalhesTopicos;
import forumHub.api.domain.topico.Topico;
import forumHub.api.repository.RespostaRepository;
import forumHub.api.repository.TopicoRepository;
import forumHub.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;


    public DadosDetalhesResposta postResposta(
            @Valid DadosCadastroResposta dados) {

        var usuario = usuarioRepository.getReferenceById((dados.usuario_id()));
        var topico = topicoRepository.getReferenceById((dados.topico_id()));

        var resposta = new Resposta (
                null,
                dados.mensagem(),
                topico,
                dados.data_criacao(),
                usuario,
                null
        );

        respostaRepository.save(resposta);
        return new DadosDetalhesResposta(resposta);
    }
}