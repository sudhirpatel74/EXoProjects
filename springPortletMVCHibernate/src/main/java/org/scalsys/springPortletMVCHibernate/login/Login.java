package org.scalsys.springPortletMVCHibernate.login;

import java.util.List;

import org.hibernate.Session;
import org.scalsys.springPortletMVCHibernate.HibernateUtil;

public class Login {

	private String email;
	private String password;
	
	
	public Login(){
		email = "This field contains Email";
		password = "this field Contains Password";
	}
	public Login(String email , String password){
		this.email = email;
		this.password = password;
	}
	public Login(Login login){
		this.email = login.getEmail();
		this.password = login.getPassword();
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validateLogin(){
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List userList  = session.createQuery("from User where username='" + getEmail() + "' and password='" + getPassword() + "'" ).list();
		System.out.println("No of user : " + userList.size());
		session.close();
		if(userList.size()==1){
			return true;
		}
		/*
		 * Static Validations
		 * if(getEmail().equals("sudhir")  && getPassword().equals("123")){
			return true;
		}
		*/
		return false;
	}
	
	
}
