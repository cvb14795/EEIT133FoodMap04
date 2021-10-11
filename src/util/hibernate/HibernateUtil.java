package util.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory FACTORY = createSessionFactory();
	
	private static SessionFactory createSessionFactory() {
		
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("./hibernate.cfg.xml").build();
		SessionFactory factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		return factory;
	}
	public static SessionFactory getSessionFactory() {
		return FACTORY;
	}
	public static void closeSessionFactory() {
		if (FACTORY != null) {
			FACTORY.close();
		}
	}
}
