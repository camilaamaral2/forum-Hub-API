package forumHub.api.domain.usuario;

import forumHub.api.domain.perfil.Perfil;
import jakarta.validation.constraints.NotBlank;


public record DadosAtualizacaoUsuario(
        @NotBlank
        String nome,
        String email,
        String senha,
        Perfil perfil) {

    }

