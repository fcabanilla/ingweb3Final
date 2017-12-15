package ar.com.magm.ti.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name = "entidades")
public class Entidad implements Serializable {

	private static final long serialVersionUID = 2464927409480955012L;
	@Id
	@GeneratedValue
	private int idEntidad;


	private String nombre;

	@Override
	public int hashCode() {
		return getIdEntidad();

	}

	@Override
	public String toString() {
		return String.format("Entidad: %s - %s", getIdEntidad(), getNombre());
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		return getIdEntidad()==((Entidad)obj).getIdEntidad();
	}
}
