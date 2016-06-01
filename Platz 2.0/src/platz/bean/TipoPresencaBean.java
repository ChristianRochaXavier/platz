package platz.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import platz.dao.TipoPresencaDAO;
import platz.model.TipoPresenca;

@ManagedBean
@SessionScoped
public class TipoPresencaBean {
	
	private TipoPresenca tipoPresenca;
	private List<TipoPresenca> tiposPresencas;
	private TipoPresenca tipoPresencaExcluir;
	private TipoPresenca tipoPresencaEditar;
	
	public TipoPresencaBean()
	{
		tipoPresenca = new TipoPresenca();
		tipoPresencaExcluir = new TipoPresenca();
		tipoPresencaEditar = new TipoPresenca();
		tiposPresencas = new TipoPresencaDAO().listarTodos();
	}
	
	
	public TipoPresenca getTipoPresenca() {
		return tipoPresenca;
	}
	public void setTipoPresenca(TipoPresenca tipoPresenca) {
		this.tipoPresenca = tipoPresenca;
	}
	
	public List<TipoPresenca> getTiposPresencas() {
		return tiposPresencas;
	}

	public void setTiposPresencas(List<TipoPresenca> tiposPresencas) {
		this.tiposPresencas = tiposPresencas;
	}


	public TipoPresenca getTipoPresencaExcluir() {
		return tipoPresencaExcluir;
	}
	public void setTipoPresencaExcluir(TipoPresenca tipoPresencaExcluir) {
		this.tipoPresencaExcluir = tipoPresencaExcluir;
	}
	public TipoPresenca getTipoPresencaEditar() {
		return tipoPresencaEditar;
	}
	public void setTipoPresencaEditar(TipoPresenca tipoPresencaEditar) {
		this.tipoPresencaEditar = tipoPresencaEditar;
	}
	
	public void salvar() {
		new TipoPresencaDAO().cadastrar(tipoPresenca);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tipo de Presença salva com Sucesso"));
		this.zerar();
	}

	public void editar() {
		new TipoPresencaDAO().cadastrar(tipoPresencaEditar);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tipo de Presença editada com Sucesso"));
		this.zerar();
	}

	public void excluir() {
		new TipoPresencaDAO().excluir(tipoPresencaExcluir);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tipo de Presença excluida com Sucesso"));
		this.zerar();
	}

	public void preparaEdicao(TipoPresenca tipopresenca) {
		this.tipoPresencaEditar = tipopresenca;
		this.editar();
	}

	public void preparaExclusao(TipoPresenca tipopresenca) {
		this.tipoPresencaExcluir = tipopresenca;
	}
	
	public void zerar()
	{
		tipoPresenca = new TipoPresenca();
		tipoPresencaExcluir = new TipoPresenca();
		tipoPresencaEditar = new TipoPresenca();
		tiposPresencas = new TipoPresencaDAO().listarTodos();
	}

}
