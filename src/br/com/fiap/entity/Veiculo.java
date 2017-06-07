package br.com.fiap.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDVEICULO")
	private Integer id;
	
	@Column(name = "MARCA")
	private String marca;
	
	@Column(name = "MODELO")
	private String modelo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="IDVEICULO")
	private Set<Multa> multas = new LinkedHashSet<Multa>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Set<Multa> getMultas() {
		return multas;
	}
	public void setMultas(Set<Multa> multas) {
		this.multas = multas;
	}
	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", multas=" + multas + "]";
	}

	
}
