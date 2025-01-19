package forumHub.api.domain.topico;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DadosCadastroTopico(
        String titulo,
        String mensagem,
        LocalDate data_criacao,
        Long usuario_id,
        Long curso_id
) {
}

