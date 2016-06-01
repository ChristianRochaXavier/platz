package platz.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import platz.dao.ContaDAO;
import platz.model.Conta;
import platz.model.Perfil;
import platz.util.EncriptAES;

@ManagedBean
@SessionScoped
public class LoginBean {

	private ContaDAO contaDAO = new ContaDAO();
	private Conta conta = new Conta();
	private Perfil perfil;

	public LoginBean() {
		this.conta = new Conta();
	}

	public ContaDAO getContaDAO() {
		return contaDAO;
	}

	public void setContaDAO(ContaDAO contaDAO) {
		this.contaDAO = contaDAO;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String logar() {

		// Criptografia
		String textoEncriptado = "";
		EncriptAES aes = new EncriptAES();
		byte[] textoEmBytesEncriptados;
		try {

			textoEmBytesEncriptados = aes.encrypt(conta.getSenha(), EncriptAES.getChaveEncriptacao());
			textoEncriptado = aes.byteParaString(textoEmBytesEncriptados);

			// Buscar conta
			conta = contaDAO.getConta(conta.getLogin(), textoEncriptado);

		} catch (Exception e) {
			e.getMessage();
		}

		if (conta == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha incorretos", "Erro no Login!"));
			conta = new Conta();
			return null;
		} else {

			if (conta.isAtivo() == true) {

				if (conta.getPerfil().equals(Perfil.ADMINISTRADOR)) {// Admin
					return "/Administrador/index?faces-redirect=true";

				} else if (conta.getPerfil().equals(Perfil.EMPRESA)) {
					return "/Empresa/index?faces-redirect=true";

				} else if (conta.getPerfil().equals(Perfil.USUARIO)) {
					return "/Usuario/index?faces-redirect=true";

				} else { // Perfil errado
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Perfil Inválido", "Erro no Login!"));

					return null;
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Conta Desativada"));
				return null;
			}
		}
	}

}
