package platz.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import platz.dao.CidadeDAO;
import platz.model.Cidade;

@ManagedBean
@SessionScoped
public class CidadeBean {
	private List<Cidade> cidades = new CidadeDAO().listarTodos();
	
	public CidadeBean() {
		cidades = new CidadeDAO().listarTodos();
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
}
