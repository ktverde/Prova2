package Factory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EManagerFactory
{
    private static final EntityManager em = Persistence.createEntityManagerFactory("sistema_empresa").createEntityManager();

    public static EntityManager getEm(){
        return em;
    }
}
