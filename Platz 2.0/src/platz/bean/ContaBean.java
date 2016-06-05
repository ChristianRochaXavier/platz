package platz.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
public class ContaBean {
	private Conta conta;
	private Conta contaExcluir;
	private List<Conta> contas = new ArrayList<Conta>();
	private List<Perfil> perfis;
	private Conta contaDetalhe;
	private boolean editar = false;

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public ContaBean() {
		perfis = Arrays.asList(Perfil.values());
		conta = null;
		contas = new ContaDAO().listarTodos();
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Conta getContaDetalhe() {
		return contaDetalhe;
	}

	public void setContaDetalhe(Conta contaDetalhe) {
		this.contaDetalhe = contaDetalhe;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Conta getContaExcluir() {
		return contaExcluir;
	}

	public void setContaExcluir(Conta contaExcluir) {
		this.contaExcluir = contaExcluir;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public void cadastrar() {
		String textoEncriptado = "";

		if (editar) {
			textoEncriptado = conta.getSenha();
		} else {
			// Parte da criptografia

			EncriptAES aes = new EncriptAES();
			byte[] textoEmBytesEncriptados;
			try {

				textoEmBytesEncriptados = aes.encrypt(conta.getSenha(), EncriptAES.getChaveEncriptacao());
				textoEncriptado = aes.byteParaString(textoEmBytesEncriptados);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		conta.setSenha(textoEncriptado);
		conta.setDataCadastro(new Date());
		new ContaDAO().cadastrar(conta);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Conta salva com sucesso"));
		this.zerar();
	}

	public void editar(Conta conta) {
		this.editar = true;
		this.conta = conta;
	}

	public void detalhes(Conta conta) {
		this.contaDetalhe = conta;
	}

	public void inverterAtividade(boolean status) {

		this.contaExcluir.setAtivo(!status);

		new ContaDAO().cadastrar(contaExcluir);
		this.zerar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Status alterado com sucesso"));

	}

	public void preparaExclusao(Conta conta) {
		this.contaExcluir = conta;
	}

	public void novaConta() {
		conta = new Conta();
	}

	public void voltar() {
		this.zerar();
	}

	public String isAtivo(boolean ativo) {
		if (ativo) {
			return "Ativo";
		} else {
			return "Inativo";
		}
	}

	public void zerar() {
		this.conta = null;
		this.editar = false;
		this.contas = new ContaDAO().listarTodos();
		this.contaDetalhe = null;
		this.contaExcluir = null;
	}
}
