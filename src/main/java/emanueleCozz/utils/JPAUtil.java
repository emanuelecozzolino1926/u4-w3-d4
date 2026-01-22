package emanueleCozz.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneEventiPU");

    private JPAUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
