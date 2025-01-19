package forumHub.api.service;

import forumHub.api.domain.topico.DadosCadastroTopico;
import forumHub.api.domain.topico.DadosDetalhesTopicos;
import forumHub.api.domain.topico.Topico;
import forumHub.api.repository.CursoRepository;
import forumHub.api.repository.TopicoRepository;
import forumHub.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public DadosDetalhesTopicos postTopico(
            @Valid DadosCadastroTopico dados) {

        var usuario = usuarioRepository.getReferenceById((dados.usuario_id()));
        var curso = cursoRepository.getReferenceById((dados.curso_id()));

        var topico = new Topico(
                null,
                dados.titulo(),
                dados.mensagem(),
                LocalDate.now(),
                usuario,
                curso
        );

        topicoRepository.save(topico);
        return new DadosDetalhesTopicos(topico);

    }

}
