package platz.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import platz.dao.CategoriaDAO;
import platz.model.CategoriaEvento;

@ManagedBean
@SessionScoped
public class CategoriaBean {
	private CategoriaEvento categoriaEvento = new CategoriaEvento();
	private List<CategoriaEvento> categoriaseventos = new ArrayList<CategoriaEvento>();
	private CategoriaEvento categoriaEventoExcluir;
	private CategoriaEvento categoriaEventoEditar = new CategoriaEvento();

	public CategoriaBean() {
		categoriaEvento = new CategoriaEvento();
		categoriaseventos = new CategoriaDAO().listarTodos();
	}

	public CategoriaEvento getCategoriaEventoExcluir() {
		return categoriaEventoExcluir;
	}

	public void setCategoriaEventoExcluir(CategoriaEvento categoriaEventoExcluir) {
		this.categoriaEventoExcluir = categoriaEventoExcluir;
	}

	public CategoriaEvento getCategoriaEvento() {
		return categoriaEvento;
	}

	public CategoriaEvento getCategoriaEventoEditar() {
		return categoriaEventoEditar;
	}

	public void setCategoriaEventoEditar(CategoriaEvento categoriaEventoEditar) {
		this.categoriaEventoEditar = categoriaEventoEditar;
	}

	public void setCategoriaEvento(CategoriaEvento categoriaevento) {
		this.categoriaEvento = categoriaevento;
	}

	public List<CategoriaEvento> getCategoriaseventos() {
		return categoriaseventos;
	}

	public void setCategoriaseventos(List<CategoriaEvento> categoriaseventos) {
		this.categoriaseventos = categoriaseventos;
	}

	public void salvar() {
		new CategoriaDAO().cadastrar(categoriaEvento);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria salva com Sucesso"));

		this.zerar();

	}

	public void editar() {
		new CategoriaDAO().cadastrar(categoriaEventoEditar);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria editada com Sucesso"));
		this.zerar();

	}

	public void excluir() {
		new CategoriaDAO().excluir(categoriaEventoExcluir);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Categoria Excluída com sucesso"));
		this.zerar();
	}

	public void prepararExclusao(CategoriaEvento categoriaEvento) {
		this.categoriaEventoExcluir = categoriaEvento;
	}

	public void prepararEdicao(CategoriaEvento categoriaEvento) {		
		this.categoriaEventoEditar = categoriaEvento;
		this.editar();
	}

	public void zerar() {
		this.categoriaseventos = new CategoriaDAO().listarTodos();
		this.categoriaEvento = new CategoriaEvento();
		this.categoriaEventoExcluir = new CategoriaEvento();
		this.categoriaEventoEditar = new CategoriaEvento();
	}

}
