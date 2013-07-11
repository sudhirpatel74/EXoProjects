package org.scalsys.springPortletMVCHibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.scalsys.springPortletMVCHibernate.SchemaModel.User;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();

	public static SessionFactory buildSessionFactory() {
			SessionFactory sessionFactory = new AnnotationConfiguration().configure().addPackage("org.scalsys.springPortletMVCHibernate.SchemaModel").addAnnotatedClass(User.class).buildSessionFactory();
			return sessionFactory;
		
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
