package br.com.hapvida.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.hapvida.commons.EspecieEnum;

@Entity
@Table(name="ANIMAL")
public class Animal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "ESPECIE", nullable = false)
	private String especie;
	
	@Column(name = "RACA", nullable = false)
	private String raca;
	
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@ManyToOne
	private Consulta consulta;
	
	@OneToMany
	private List<Tutor> tutor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Tutor> getTutor() {
		return tutor;
	}

	public void setTutor(List<Tutor> tutor) {
		this.tutor = tutor;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

}
