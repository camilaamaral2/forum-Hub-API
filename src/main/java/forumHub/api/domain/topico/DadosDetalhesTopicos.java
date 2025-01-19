package forumHub.api.domain.topico;

import java.time.LocalDate;

public record DadosDetalhesTopicos (Long id,
                                    String titulo,
                                    String mensagem,
                                    LocalDate data_criacao,
                                    Status status,
                                    String usuarioNome,
                                    String cursoNome,
                                    String cursoCategoria){

        public DadosDetalhesTopicos(Topico topico) {
            this(topico.getId(),
                    topico.getTitulo(),
                    topico.getMensagem(),
                    topico.getData_criacao(),
                    topico.getStatus(),
                    topico.getUsuario().getNome(),
                    topico.getCurso().getNome(),
                    topico.getCurso().getCategoria().name());
        }
}
