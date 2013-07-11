package org.scalsys.springPortletMVCHibernate.login;

import java.util.List;

import org.hibernate.classic.Session;
import org.scalsys.springPortletMVCHibernate.HibernateUtil;
import org.scalsys.springPortletMVCHibernate.SchemaModel.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Login.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Login login = (Login)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List<User> users=   session.createQuery("from User where username ='" + login.getEmail() + "' and password='" + login.getPassword() +"'").list();
		
		if(users.size()==0){
			errors.reject("wrongcredential","wrong Username of Password!!");
		}
	}

}
