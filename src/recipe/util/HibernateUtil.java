package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory FACTORY = createSessionFactory();
	
	private static SessionFactory createSessionFactory() {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		// SessionFactory:資料庫物件 -> 取得資料庫資源
		SessionFactory factory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		return factory;
	}
	
	public static SessionFactory getSessionFactory() {
		return FACTORY;
	}
	
	public static void closeSessionFactory() {
		if(FACTORY != null) {
			FACTORY.close();
		}
	}
}
