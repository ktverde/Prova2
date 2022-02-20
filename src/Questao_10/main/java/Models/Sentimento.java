package Models;

import javax.persistence.*;

@Entity
@Table(name = "sentimentos")
public class Sentimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuariosDB usuario;
    private String mensagem, sentimento;

    public Sentimento() {
    }

    public Sentimento(String msg, String sentimento, UsuariosDB usuario) {
        this.mensagem = msg;
        this.sentimento = sentimento;
        this.usuario = usuario;
    }

    public UsuariosDB getUsuario() {
        return usuario;
    }

    public String getSentimento() {
        return sentimento;
    }
}
