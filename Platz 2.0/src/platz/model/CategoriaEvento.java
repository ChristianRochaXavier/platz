package platz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class CategoriaEvento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String categoria;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
		
	@NotEmpty(message= "O campo Categoria não pode ficar vazio")
	@NotNull
	@Length(min = 3, max = 30, message = "O campo Categoria dever ter entre 3 e 30 caracteres")
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria){
		this.categoria = categoria;
	}
	
}
