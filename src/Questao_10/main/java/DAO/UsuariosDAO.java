package DAO;

import Models.UsuariosDB;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuariosDAO
{
    private EntityManager em;

    public UsuariosDAO(EntityManager em){
        this.em = em;
    }

    public void salvar(UsuariosDB usuarios){
        this.em.getTransaction().begin();
        this.em.persist(usuarios);
        em.getTransaction().commit();
    }
    public void atualizar(UsuariosDB usuarios){
        this.em.merge(usuarios);
    }
    public void remover(UsuariosDB usuarios){
        this.em.remove(em.merge(usuarios));
    }

    public List<UsuariosDB> listarTodos(){
        String jpql = "SELECT u FROM UsuariosDB u";
        return em.createQuery(jpql, UsuariosDB.class).getResultList();
    }




}
