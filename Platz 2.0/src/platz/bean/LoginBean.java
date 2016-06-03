package platz.bean;

import java.io.IOException;
import java.util.Date;

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

	public void ultimoAcesso(Conta conta) {		
		conta.setUltimoAcesso(new Date());		
		new ContaDAO().cadastrar(conta);
	}
	public void logoff() throws IOException{		
		System.out.println(conta.getLogin());
		this.conta = null;
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
					this.ultimoAcesso(conta);
					return "/Administrador/index?faces-redirect=true";

				} else if (conta.getPerfil().equals(Perfil.EMPRESA)) {
					this.ultimoAcesso(conta);
					return "/Empresa/index?faces-redirect=true";

				} else if (conta.getPerfil().equals(Perfil.USUARIO)) {
					this.ultimoAcesso(conta);
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
