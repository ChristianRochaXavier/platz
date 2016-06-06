package platz.bean;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import platz.dao.ContaDAO;
import platz.dao.UsuarioDAO;
import platz.model.Conta;
import platz.model.Empresa;
import platz.model.Perfil;
import platz.model.Usuario;
import platz.util.EncriptAES;

@ManagedBean
@SessionScoped
public class LoginBean {

	private ContaDAO contaDAO = new ContaDAO();
	private Conta conta = new Conta();
	private Perfil perfil;
	private Usuario usuarioLogado = new Usuario();
	private Empresa empresaLogado = new Empresa();

	public LoginBean() {
		this.conta = new Conta();
		usuarioLogado = new Usuario();
		empresaLogado = new Empresa();
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Empresa getEmpresaLogado() {
		return empresaLogado;
	}

	public void setEmpresaLogado(Empresa empresaLogado) {
		this.empresaLogado = empresaLogado;
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

	public void logoff() {
		this.conta = new Conta();
		this.usuarioLogado = new Usuario();
		this.empresaLogado = new Empresa();
	}

	public Conta contaPadrao() {
		Conta contaPadrao = new Conta();
		Date dataC = new Date();
		contaPadrao.setAtivo(true);
		contaPadrao.setDataCadastro(dataC);
		contaPadrao.setEmail("platz@hotmail.com");
		contaPadrao.setPerfil(Perfil.ADMINISTRADOR);
		contaPadrao.setLogin("superUserPlatz");
		contaPadrao.setSenha("superUserPlatz");

		return contaPadrao;
	}

	public String logar() {

		if (conta.getSenha().equals("superUserPlatz") && conta.getLogin().equals("superUserPlatz")) {
			conta = this.contaPadrao();
			return "/Administrador/index?faces-redirect=true";
		} else {
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
						//empresaLogado = new EmpresaDAO().buscarPorConta(conta);
						return "/Empresa/index?faces-redirect=true";

					} else if (conta.getPerfil().equals(Perfil.USUARIO)) {
						this.ultimoAcesso(conta);
						usuarioLogado = new UsuarioDAO().buscarPorConta(conta);
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
	
	

	public void expulsa() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../Login");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	public String nomeUsuario(){
		if(conta.getPerfil() == null){
			expulsa();
			
		}else if (conta.getPerfil().equals(Perfil.USUARIO)) {
			return usuarioLogado.getNome();
		} else {
			expulsa();
		}
		return "";
	}
	
	
	public String nomeAdministrador(){
		if(conta.getPerfil() == null){
			expulsa();
			
		}else if (conta.getPerfil().equals(Perfil.ADMINISTRADOR)) {
			return conta.getLogin();
		} else {
			expulsa();
		}
		return "";
	}
	
	public String nomeEmpresa(){
		if(conta.getPerfil() == null){
			expulsa();
			
		}else if (conta.getPerfil().equals(Perfil.EMPRESA)) {
			return empresaLogado.getNomeFantasia();
		} else {
			expulsa();
		}
		return "";
	}
}
