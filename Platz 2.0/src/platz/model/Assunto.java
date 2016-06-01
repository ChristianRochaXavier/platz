package platz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Assunto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String assunto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotEmpty(message = "O campo assunto não pode ficar vazio")
	@Length(max = 30, min = 3, message = "O campo assunto deve ter entre 3 e 30 caracteres")
	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

}
