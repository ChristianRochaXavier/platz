package platz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Cidade {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "idEstado")
	private Estado estado;
	private String nome;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotNull
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	@NotNull
	@NotEmpty	
	public String getNome() {
		return nome;
	}
	public void setNome(String cidade) {
		this.nome = cidade;
	}
	
	
}
