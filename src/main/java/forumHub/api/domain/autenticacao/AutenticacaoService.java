package forumHub.api.domain.autenticacao;

import forumHub.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if ((usuarioRepository.findByEmail(username)) == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + username);
        }

        return usuarioRepository.findByEmail(username);
    }
}
