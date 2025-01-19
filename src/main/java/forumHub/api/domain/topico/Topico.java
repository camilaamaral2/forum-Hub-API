package forumHub.api.domain.topico;

import forumHub.api.domain.curso.Curso;
import forumHub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDate;

@Entity
@Table(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String mensagem;

    @Column
    private LocalDate data_criacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    public Topico(Long id, String titulo, String mensagem, LocalDate data_criacao, Usuario usuario, Curso curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.status = Status.ABERTO;
        this.data_criacao = data_criacao;
        this.usuario = usuario;
        this.curso = curso;
    }

    Topico() {
    }

    public Topico(@Valid DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.data_criacao = dados.data_criacao();
        this.status = Status.ABERTO;
    }

    public void atualizarTopicosMensagem(DadosAtualizacaoTopicoMensagem dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
    }

    public void atualizarTopicosStatus(DadosAtualizacaoTopicoStatus dados){

            if (this.status == Status.ABERTO){
                this.status = Status.RESOLVIDO;
            }
            else if (this.status == Status.RESOLVIDO){
                this.status = Status.REABERTO;
            }
            else if (this.status == Status.REABERTO){
                this.status = Status.RESOLVIDO;
            }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}


