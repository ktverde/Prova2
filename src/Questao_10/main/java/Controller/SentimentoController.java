package Controller;

import DAO.SentimentoDAO;
import Factory.EManagerFactory;
import Models.Sentimento;


import javax.persistence.EntityManager;

public class SentimentoController
{
    private SentimentoDAO dao;
    private EntityManager em;

    public SentimentoController(){
        this.em = EManagerFactory.getEm();
        em.getTransaction().begin();
        SentimentoDAO dao = new SentimentoDAO(em);
    }
    public void salvar(Sentimento sentimento){
        dao.salvar(sentimento);
        System.out.println("Salvando");
    }
}
