package platz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class TipoPresenca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String tipoPresenca;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty(message = "O tipo de presença deve ser informado")
	public String getTipoPresenca() {
		return tipoPresenca;
	}
	public void setTipoPresenca(String tipoPresenca) {
		this.tipoPresenca = tipoPresenca;
	}
}
