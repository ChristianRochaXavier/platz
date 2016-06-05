package platz.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import platz.dao.EstadoDAO;
import platz.model.Estado;

@ManagedBean
@SessionScoped
public class EstadoBean {
	private List<Estado> estados = new EstadoDAO().listarTodos();

	public EstadoBean() {
		estados = new EstadoDAO().listarTodos();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
}
