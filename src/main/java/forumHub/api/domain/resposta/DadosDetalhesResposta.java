package forumHub.api.domain.resposta;


import java.time.LocalDate;

public record DadosDetalhesResposta(Long id,
                                    String mensagem,
                                    Long topico,
                                    LocalDate data_criacao,
                                    String usuarioNome,
                                    Solucao solucao
) {

    public DadosDetalhesResposta (Resposta resposta) {
        this(resposta.getId(),
                resposta.getMensagem(),
                resposta.getTopico().getId(),
                resposta.getData_criacao(),
                resposta.getUsuario().getNome(),
                resposta.getSolucao());
    }
}

