package platz.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import platz.dao.AssuntoDAO;
import platz.model.Assunto;

@ManagedBean
@SessionScoped
public class AssuntoBean {
	private Assunto assunto;
	private List<Assunto> assuntos;
	private Assunto assuntoExcluir;
	private Assunto assuntoEditar;

	public AssuntoBean() {
		assunto = new Assunto();
		assuntoEditar = new Assunto();
		assuntoExcluir = new Assunto();
		assuntos = new AssuntoDAO().listarTodos();
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public Assunto getAssuntoExcluir() {
		return assuntoExcluir;
	}

	public void setAssuntoExcluir(Assunto assuntoExcluir) {
		this.assuntoExcluir = assuntoExcluir;
	}

	public Assunto getAssuntoEditar() {
		return assuntoEditar;
	}

	public void setAssuntoEditar(Assunto assuntoEditar) {
		this.assuntoEditar = assuntoEditar;
	}

	public void salvar() {
		new AssuntoDAO().cadastrar(assunto);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Assunto salva com Sucesso"));
		this.zerar();
	}

	public void editar() {
		new AssuntoDAO().cadastrar(assuntoEditar);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Assunto editado com Sucesso"));
		this.zerar();
	}

	public void excluir() {
		new AssuntoDAO().excluir(assuntoExcluir);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Assunto excluido com Sucesso"));
		this.zerar();
	}

	public void preparaEdicao(Assunto assunto) {
		this.assuntoEditar = assunto;
		this.editar();
	}

	public void preparaExclusao(Assunto assunto) {
		this.assuntoExcluir = assunto;
	}

	public void zerar() {
		assunto = new Assunto();
		assuntoEditar = new Assunto();
		assuntoExcluir = new Assunto();
		assuntos = new AssuntoDAO().listarTodos();
	}
}
