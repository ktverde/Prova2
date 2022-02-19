package Models;

import javax.persistence.*;

@Entity
@Table(name = "sentimentos")
public class Sentimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private UsuariosDB usuario;
    private String mensagem, sentimento;

    public Sentimento() {
    }

    public Sentimento(String msg) {
        this.mensagem = msg;
    }
}
