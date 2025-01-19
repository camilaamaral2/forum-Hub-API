package forumHub.api.domain.resposta;

import forumHub.api.domain.usuario.Usuario;
import forumHub.api.domain.topico.Topico;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDate;

@Entity
@Table(name = "Resposta")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    @Temporal(TemporalType.DATE)
    private LocalDate data_criacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Solucao solucao;

    Resposta(){}

    public Resposta(Long id, String mensagem, Topico topico, LocalDate data_criacao, Usuario usuario, Solucao solucao) {
        this.id = id;
        this.mensagem = mensagem;
        this.topico = topico;
        this.data_criacao = data_criacao;
        this.usuario = usuario;
        this.solucao = solucao;
    }

    public Resposta(@Valid DadosCadastroResposta dados){
        this.mensagem = dados.mensagem();
        this.data_criacao = dados.data_criacao();
    }

    public void atualizarInformacoesResposta (DadosAtualizacaoResposta dados){
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Solucao getSolucao() {
        return solucao;
    }

    public void setSolucao(Solucao solucao) {
        this.solucao = solucao;
    }
}
