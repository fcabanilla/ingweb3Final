package ar.com.magm.ti.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name = "roles")
public class Role implements Serializable {

	private static final long serialVersionUID = 5914170182774536215L;

	@Column(nullable = false)
	private String description;
	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	@ManyToMany( /* cascade = { CascadeType.REMOVE }, */fetch = FetchType.EAGER)
	@JoinTable(name="roleprivileges", joinColumns = {
			@JoinColumn(name = "idRole", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "idPrivilege", referencedColumnName = "id") })
	private Set<Privilege> privileges;

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	@Override
	public boolean equals(Object obj) {
		return ((Role) obj).getId() == getId() || ((Role) obj).getName().equals(getName());
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Role: [%d] %s", getId(), getName());
	}

}
