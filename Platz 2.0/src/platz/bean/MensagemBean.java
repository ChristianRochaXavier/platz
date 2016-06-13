package platz.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import platz.dao.MensagemDAO;
import platz.model.Mensagem;

@ManagedBean
@SessionScoped
public class MensagemBean {
	private Mensagem mensagem;
	private Mensagem mensagemDetalhe;
	private List<Mensagem> mensagens;

	public MensagemBean() {
		mensagem = new Mensagem();
		mensagemDetalhe = new Mensagem();
		mensagens = new MensagemDAO().listarTodos();
	}

	public void zerar() {
		mensagem = new Mensagem();
		mensagemDetalhe = new Mensagem();
		mensagens = new MensagemDAO().listarTodos();
	}
	public void cadastrar(){
		new MensagemDAO().cadastrar(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("mensagem enviada com sucesso"));
		this.zerar();
	}
	public void mensagemDetalhe(Mensagem mensagem) {
		mensagemDetalhe = mensagem;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Mensagem getMensagemDetalhe() {
		return mensagemDetalhe;
	}

	public void setMensagemDetalhe(Mensagem mensagemDetalhe) {
		this.mensagemDetalhe = mensagemDetalhe;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

}
