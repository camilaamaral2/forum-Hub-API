package forumHub.api.domain.usuario;

import forumHub.api.domain.perfil.Perfil;

public record DadosDetalhesUsuarios (Long id,
                                     String nome,
                                     String email,
                                     Perfil perfil){

    public DadosDetalhesUsuarios (Usuario usuario) {
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil());
    }

}
