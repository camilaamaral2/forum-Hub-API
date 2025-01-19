package forumHub.api.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroResposta(
        @NotBlank
        String mensagem,

        Long usuario_id,

        @NotNull
        LocalDate data_criacao,

        Long topico_id
) {
}
