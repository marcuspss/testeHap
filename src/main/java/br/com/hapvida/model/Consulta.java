package br.com.hapvida.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.hapvida.commons.StatusEnum;

@Entity
@Table(name="CONSULTA")
public class Consulta  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "DATA_CONSULTA", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataConsulta;
	
	@Column(name = "STATUS", nullable = false)
	private String status;

	@OneToMany
	private List<Animal> animal;
	
	@OneToMany
	private List<Veterinario> veterinario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public List<Animal> getAnimal() {
		return animal;
	}

	public void setAnimal(List<Animal> animal) {
		this.animal = animal;
	}

	public List<Veterinario> getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(List<Veterinario> veterinario) {
		this.veterinario = veterinario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
