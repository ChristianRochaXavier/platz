package platz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Presenca {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "idTipoPresenca")
	private TipoPresenca TipoPresenca;
	@ManyToOne
	@JoinColumn(name = "idEvento")
	private Evento Evento;
	@ManyToOne
	@JoinColumn(name = "idConta")
	private Conta Conta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	public TipoPresenca getTipoPresenca() {
		return TipoPresenca;
	}

	public void setTipoPresenca(TipoPresenca tipoPresenca) {
		TipoPresenca = tipoPresenca;
	}

	@NotNull
	public Evento getEvento() {
		return Evento;
	}

	public void setEvento(Evento evento) {
		Evento = evento;
	}

	@NotNull
	public Conta getConta() {
		return Conta;
	}

	public void setConta(Conta conta) {
		Conta = conta;
	}
}
