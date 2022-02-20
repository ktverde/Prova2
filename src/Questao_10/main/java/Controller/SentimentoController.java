package Controller;

import DAO.SentimentoDAO;
import Factory.EManagerFactory;
import Models.Sentimento;
import Models.UsuariosDB;


import javax.persistence.EntityManager;
import java.util.List;

public class SentimentoController
{
    private SentimentoDAO dao;
    private EntityManager em;
    private UsuariosDB usuario;

    public SentimentoController(){
        this.em = EManagerFactory.getEm();
        dao = new SentimentoDAO(em);
    }
    public void salvar(Sentimento sentimento){
        dao.salvar(sentimento);
        System.out.println("Salvando");
    }

    public List<Integer> buscarTodos(){
        return dao.buscarTodos();
    }

    public UsuariosDB getUsuario() { return usuario; }
    public void setUsuario(UsuariosDB usuario) { this.usuario = usuario; }
}
