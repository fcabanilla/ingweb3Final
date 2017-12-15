package ar.com.magm.ti.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name = "users")
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	private boolean accountEnabled = true;

	private boolean accountExpired = false;

	private boolean accountLocked = false;

	private boolean credentialsExpired = false;

	@Column(unique = true)
	private String email;

	private String firstName;

	@Id
	@GeneratedValue
	private int idUser;

	private String lastName;

	private String password;

	@ManyToMany( /* cascade = { CascadeType.REMOVE }, */fetch = FetchType.EAGER)
	@JoinTable(name = "userroles", joinColumns = {
			@JoinColumn(name = "idUser", referencedColumnName = "idUser") }, inverseJoinColumns = {
					@JoinColumn(name = "idRole", referencedColumnName = "id") })
	private Set<Role> roles;

	@Column(unique = true, nullable = false)
	private String username;

	public boolean containsAuthority(String auth) {
		for (GrantedAuthority ga : getAuthorities()) {
			if (ga.getAuthority().equalsIgnoreCase(auth))
				return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		return ((User) obj).getIdUser() == getIdUser() || ((User) obj).getUsername().equals(getUsername());
	}

	@Override  
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> l = new ArrayList<String>();
		for (Role r : roles) {
			l.add(r.getName());
			for (Privilege p : r.getPrivileges())
				l.add(p.getName());
		}
		for (Privilege p : getPrivileges())
			l.add(p.getName());

		return AuthorityUtils.createAuthorityList(l.toArray(new String[0]));
		// return authorities;
	}
	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getIdUser() {
		return idUser;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public String getUsername() {
		return username;
	}

	@ManyToMany( /* cascade = { CascadeType.REMOVE }, */fetch = FetchType.EAGER)
	@JoinTable(name = "userprivileges", joinColumns = {
			@JoinColumn(name = "idUser", referencedColumnName = "idUser") }, inverseJoinColumns = {
					@JoinColumn(name = "idPrivilege", referencedColumnName = "id") })
	private Set<Privilege> privileges;

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public int hashCode() {
		return getIdUser();
	}

	public boolean isAccountEnabled() {
		return accountEnabled;
	}
	public boolean isAccountExpired() {
		return accountExpired;
	}
	public boolean isAccountLocked() {
		return accountLocked;
	}
	@Transient
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}
	@Transient
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}
	@Transient
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}
	@Transient
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return accountEnabled;
	}
	public void setAccountEnabled(boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return String.format("User: [%d] %s {enabled:%s, expired:%s, locked:%s, credentialsExpired:%s}", getIdUser(),
				getUsername(), isAccountEnabled(), isAccountExpired(), isAccountLocked(), isCredentialsExpired());
	}

}
