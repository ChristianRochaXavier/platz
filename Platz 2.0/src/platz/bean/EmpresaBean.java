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
	static final String CAMINHOIMAGEM = "/resources/img/empresaPerfil/";

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
		System.out.println("Teste");
	}

	public void voltar() {
		this.zerar();
	}

	// M�todo que v� o evento de upload do p:upload
	public void upload(FileUploadEvent event) {

		// System.out.println("Entrou no m�todo upload");
		// System.out.println("Nome do Arquivo: " +
		// event.getFile().getFileName());

		// Pega o caminho completo do diret�rio
		String caminhoCompleto = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").toString()
				+ "\\resources\\img\\empresaPerfil\\";

		// System.out.println("Caminho: " + caminhoCompleto);
		// System.out.println("");

		try {
			// Pega os bytes da imagem
			byte[] input = event.getFile().getContents();

			// Cria um FileoutputStream que trabalhar� no diret�rio completo
			FileOutputStream fos = new FileOutputStream(caminhoCompleto + event.getFile().getFileName());

			// Salva a imagem
			fos.flush();
			fos.write(input);
			fos.close();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload feito com sucesso"));

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			System.out.println("Erro: " + e.getCause());

		}

		// Seta o caminho da imagem para ser salvo no banco baseado no caminho
		// est�tico criado acima
		empresa.setImagemPerfil(CAMINHOIMAGEM + event.getFile().getFileName());
		// System.out.println(usuario.getImagemPerfil());
	}

	public String cadastrarAreaLivre() {
		this.cadastrar();
		return "login.jsf?faces-redirect=true";
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
		this.empresa = new EmpresaDAO().buscarPorId(empresa.getId());
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
		this.empresaDetalhe = new EmpresaDAO().buscarPorId(empresa.getId());
	}

	public void novaEmpresa() {
		rendered = false;
	}

	public String pegarImagem(Empresa empresa) {

		if (empresa.getImagemPerfil() == null || empresa.getImagemPerfil().equals("")) {
			return CAMINHOIMAGEM + "empresa.png";
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
