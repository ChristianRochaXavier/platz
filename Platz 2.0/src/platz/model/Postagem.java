package platz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date data;
	@ManyToOne
	@JoinColumn(name = "idConta")
	private Conta conta;
	@ManyToOne
	@JoinColumn(name = "idEvento")
	private Evento evento;
	private String conteudo;
	private String caminhoImagem;
	private String caminhoVideo;
	private boolean bloqueado = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@NotNull
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@NotNull
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@NotNull
	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public String getCaminhoVideo() {
		return caminhoVideo;
	}

	public void setCaminhoVideo(String caminhoVideo) {
		this.caminhoVideo = caminhoVideo;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

}
