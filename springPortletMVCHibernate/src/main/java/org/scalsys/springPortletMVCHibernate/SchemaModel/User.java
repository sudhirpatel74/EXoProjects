package org.scalsys.springPortletMVCHibernate.SchemaModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;

	
	public User(){
		this.id=1;
		this.username = "this field contains User Name";
		this.password = "this field contains Password";
	}
	public User(Integer id , String userName , String password){
		this.id = id;
		this.username = userName;
		this.password = password;
	}
	public User(User user){
		this.id = user.getId();
		this.password = user.getPassword();
		this.username = user.getUsername();
	}
	
	public Integer getUserId(){
		 return getId();
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String userInfo ="----------------------------------------\n User Id : " + getId() + "\n User Name : " + getUsername() +" \n Password : " + getPassword();
		return userInfo;
	}
	
		
}
