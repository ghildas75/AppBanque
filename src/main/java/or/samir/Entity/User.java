package or.samir.Entity;

import java.io.Serializable;
import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
@Entity
public class User implements Serializable {
@Id
private String username;
private String password;
private boolean isActived;
@ManyToMany
@JoinTable(name="USERS_ROLES")
private Collection<Role> roles;
public User(String username, String password, boolean isActived, Collection<Role> roles) {
	super();
	this.username = username;
	this.password = password;
	this.isActived = isActived;
	this.roles = roles;
}
public User() {
	super();
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public boolean isActived() {
	return isActived;
}
public void setActived(boolean isActived) {
	this.isActived = isActived;
}
public Collection<Role> getRoles() {
	return roles;
}
public void setRoles(Collection<Role> roles) {
	this.roles = roles;
}




}
