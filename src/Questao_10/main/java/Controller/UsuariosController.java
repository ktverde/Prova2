package Controller;

import DAO.UsuariosDAO;
import Factory.EManagerFactory;
import Models.UsuariosDB;
import org.jboss.jandex.Main;

import javax.persistence.EntityManager;
import java.util.List;

public class UsuariosController
{
    private UsuariosDAO dao;
    private EntityManager em;

    public UsuariosController(){
        this.em = EManagerFactory.getEm();
        dao = new UsuariosDAO(this.em);
    }
    public void salvar(UsuariosDB usuarios){
        dao.salvar(usuarios);
        System.out.println("Salvando");

    }

    public List<UsuariosDB> listarTodos(){
        return dao.listarTodos();
    }

}
