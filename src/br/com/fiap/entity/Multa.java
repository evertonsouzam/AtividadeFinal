package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "multa")
public class Multa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDMULTA")
	private Integer id;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Veiculo veiculo;
	
	@Column(name = "VALOR")
	private double valor;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "Multa [id=" + id + ", descricao=" + descricao + ", valor=" + valor + "]";
	}
	
}
