package platz.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UtilBean {
	private boolean cadastroUsuarioEmpresa;

	public UtilBean() {
		
	}

	public void alterarFormCadastroParaUsuario() {
		cadastroUsuarioEmpresa = false;
	}
	public void alterarFormCadastroParaEmpresa() {
		cadastroUsuarioEmpresa = true;
	}

	public boolean isCadastroUsuarioEmpresa() {
		return cadastroUsuarioEmpresa;
	}

	public void setCadastroUsuarioEmpresa(boolean cadastroUsuarioEmpresa) {
		this.cadastroUsuarioEmpresa = cadastroUsuarioEmpresa;
	}

}
