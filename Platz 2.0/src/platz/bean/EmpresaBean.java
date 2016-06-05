package platz.bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import platz.dao.EmpresaDAO;
import platz.model.Cidade;
import platz.model.Conta;
import platz.model.Empresa;
import platz.model.Endereco;
import platz.model.Estado;
import platz.model.Perfil;
import platz.util.EncriptAES;

@ManagedBean
@SessionScoped
public class EmpresaBean {

	private Empresa empresa = new Empresa();
	private Empresa empresaExclusao = new Empresa();
	private List<Empresa> empresas = new EmpresaDAO().listarTodos();
	private Empresa empresaDetalhe = new Empresa();
	private Empresa empresaStatus = new Empresa();
	// caso true mostra a lista, caso false o formulario
	private boolean rendered = true;
	private boolean editar = false;

	// Caminho da imagem estatico
	static final String CAMINHOIMAGEM = "/resources/img/userPerfil/";

	public EmpresaBean() {
		empresa = new Empresa();
		empresa.setConta(new Conta());
		empresa.setEndereco(new Endereco());
		empresa.getEndereco().setCidade(new Cidade());
		empresa.getEndereco().getCidade().setEstado(new Estado());
		empresaExclusao = new Empresa();
		empresas = new EmpresaDAO().listarTodos();
		empresaDetalhe = new Empresa();
		rendered = true;
		editar = false;
	}

	public void voltar() {
		this.zerar();
	}

	// Método que vê o evento de upload do p:upload
	public void upload(FileUploadEvent event) {

		System.out.println("Entrou no método upload");

		System.out.println("Nome do Arquivo: " + event.getFile().getFileName());

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(event.getFile().getFileName() + " is uploaded."));

		// Pega o caminho completo do diretório
		String caminhoCompleto = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").toString()
				+ "\\resources\\img\\userPerfil\\";

		System.out.println("Caminho: " + caminhoCompleto);
		System.out.println("");

		try {
			// Pega os bytes da imagem
			byte[] input = event.getFile().getContents();

			// Cria um FileoutputStream que trabalhará no diretório completo
			FileOutputStream fos = new FileOutputStream(caminhoCompleto + event.getFile().getFileName());

			// Salva a imagem
			fos.flush();
			fos.write(input);
			fos.close();

			System.out.println("Upload OK");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		// Seta o caminho da imagem para ser salvo no banco baseado no caminho
		// estático criado acima
		empresa.setImagemPerfil(CAMINHOIMAGEM + event.getFile().getFileName());
		// System.out.println(usuario.getImagemPerfil());
	}

	public void cadastrar() {

		// Parte da criptografia
		String textoEncriptado = "";
		if (editar) {
			textoEncriptado = empresa.getConta().getSenha();
		} else {
			EncriptAES aes = new EncriptAES();
			byte[] textoEmBytesEncriptados;
			try {

				textoEmBytesEncriptados = aes.encrypt(empresa.getConta().getSenha(), EncriptAES.getChaveEncriptacao());
				textoEncriptado = aes.byteParaString(textoEmBytesEncriptados);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		empresa.getConta().setSenha(textoEncriptado);
		empresa.getConta().setPerfil(Perfil.EMPRESA);
		empresa.getConta().setDataCadastro(new Date());
		System.out.println(empresa.getNomeFantasia());
		System.out.println(empresa.getConta().getLogin());
		System.out.println(empresa.getEndereco().getCep());
		System.out.println(empresa.getEndereco().getCidade().getNome());
		System.out.println(empresa.getEndereco().getCidade().getEstado().getUf());
		new EmpresaDAO().cadastrar(empresa);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empresa Salva com sucesso"));
		this.zerar();
	}

	public void editar(Empresa empresa) {
		this.empresa = empresa;
		this.editar = true;
		rendered = false;
	}

	public void inverterAtividade(boolean status) {

		empresaStatus.getConta().setAtivo(!status);

		new EmpresaDAO().cadastrar(empresaStatus);
		this.zerar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Status alterado com sucesso"));

	}

	public void preparaStatus(Empresa empresa) {
		this.empresaStatus = empresa;
	}

	public void detalhes(Empresa empresa) {
		this.empresaDetalhe = empresa;
	}

	public void novaEmpresa() {
		rendered = false;
	}

	public String pegarImagem(Empresa empresa) {

		if (empresa.getImagemPerfil() == null || empresa.getImagemPerfil().equals("")) {
			return "/resources/img/userPerfil/user.png";
		} else {
			return empresa.getImagemPerfil();
		}

	}

	public void zerar() {
		rendered = true;
		editar = false;
		empresa = new Empresa();
		empresa.setConta(new Conta());
		empresa.setEndereco(new Endereco());
		empresa.getEndereco().setCidade(new Cidade());
		empresa.getEndereco().getCidade().setEstado(new Estado());
		empresaDetalhe = new Empresa();
		empresaStatus = new Empresa();
		empresas = new EmpresaDAO().listarTodos();
	}

	// getters and setters
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Empresa getEmpresaExclusao() {
		return empresaExclusao;
	}

	public void setEmpresaExclusao(Empresa empresaExclusao) {
		this.empresaExclusao = empresaExclusao;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresaDetalhe() {
		return empresaDetalhe;
	}

	public void setEmpresaDetalhe(Empresa empresaDetalhe) {
		this.empresaDetalhe = empresaDetalhe;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public Empresa getEmpresaStatus() {
		return empresaStatus;
	}

	public void setEmpresaStatus(Empresa empresaStatus) {
		this.empresaStatus = empresaStatus;
	}

}
