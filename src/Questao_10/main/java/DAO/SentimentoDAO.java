package DAO;

import Models.Produto;
import Models.Sentimento;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class SentimentoDAO
{
    private EntityManager em;

    public SentimentoDAO(EntityManager em){
        this.em = em;
    }

    public void salvar(Sentimento sentimento){
        this.em.persist(sentimento);
        em.flush();
    }
    public void atualizar(Sentimento sentimento){
        this.em.merge(sentimento);
    }
    public void remover(Sentimento sentimento){
        this.em.remove(em.merge(sentimento));
    }

    public List<Sentimento> buscarTodos(){
        String jpql = "SELECT s FROM Sentimento s";
        return em.createQuery(jpql, Sentimento.class).getResultList();
    }




}
