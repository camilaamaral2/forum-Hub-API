package forumHub.api.controller;

import forumHub.api.domain.usuario.DadosAtualizacaoUsuario;
import forumHub.api.domain.usuario.Usuario;
import forumHub.api.domain.usuario.DadosCadastroUsuario;
import forumHub.api.domain.usuario.DadosDetalhesUsuarios;
import forumHub.api.repository.UsuarioRepository;

import jakarta.validation.Valid;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/{id}")
    public ResponseEntity getUsuarioById (@PathVariable Long id){
        var usuarios = usuarioRepository.getReferenceById(id);
            return ResponseEntity.ok(new DadosDetalhesUsuarios(usuarios));
    }

    @GetMapping
    public ResponseEntity <Page<DadosDetalhesUsuarios>> getUsuarios (@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = usuarioRepository.findAll(paginacao).map(DadosDetalhesUsuarios::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity postUsuario(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        try {
            var usuario = new Usuario(dados);
            var senha = encoder.encode(usuario.getPassword());
            usuario.setSenha(senha);
            usuarioRepository.save(usuario);

            var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhesUsuarios(usuario));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity putUsuario(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoUsuario dados) {
       try {
           var usuario = usuarioRepository.getReferenceById(id);
           usuario.atualizarInformacoesUsuario(dados);
           var senha = encoder.encode(usuario.getPassword());
           usuario.setSenha(senha);

           return ResponseEntity.ok(new DadosDetalhesUsuarios(usuario));
       }
       catch (Exception e) {
           e.printStackTrace();
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUsuario(@PathVariable Long id){
        try {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}


    //logica de pesquisar TODOS ---- GET (localhost:8080/usuarios)
    //TESTAR NO INSOMNIA

    //logica de pesquisar por ID ou NOME ---- GET (localhost:8080/usuarios/ID OU NOME)
    //TESTAR NO INSOMNIA

    //logica de criar ---- POST (localhost:8080/usuarios)
    //TESTAR NO INSOMNIA

    //logica de atualizar ---- PUT (localhost:8080/usuarios/id)
    //TESTAR NO INSOMNIA

    //logica de deletar ----- DELETE (localhost:8080/usuarios/id)
    //TESTAR NO INSOMNIA


