package Models;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuariosDB
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String usuario;
    private String nome;
    private String senha;

    public UsuariosDB() {
    }

    public UsuariosDB(String nome, String usuario, String senha) {
        this.usuario = usuario;
        this.nome = nome;
        this.senha = senha;
    }

    public int getId() { return id; }
    public String getUsuario() { return usuario; }
    public String getNome() { return nome; }
    public String getSenha() { return senha; }
}
