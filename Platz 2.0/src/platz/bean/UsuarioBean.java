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

import platz.dao.UsuarioDAO;
import platz.model.Cidade;
import platz.model.Conta;
import platz.model.Endereco;
import platz.model.Estado;
import platz.model.Perfil;
import platz.model.Usuario;
import platz.util.EncriptAES;

@ManagedBean
@SessionScoped
public class UsuarioBean {
	private Usuario usuario;
	private Usuario usuarioDetalhe;
	private Usuario usuarioStatus;
	private List<Usuario> usuarios;
	private boolean rendered = true;// caso true mostra a lista, caso false o
									// formulario
	private boolean editar = false;

	// Caminho da imagem estatico
	static final String CAMINHOIMAGEM = "/resources/img/userPerfil/";

	public UsuarioBean() {
		usuario = new Usuario();
		usuario.setConta(new Conta());
		usuario.setEndereco(new Endereco());
		usuario.getEndereco().setCidade(new Cidade());
		usuario.getEndereco().getCidade().setEstado(new Estado());
		usuarioDetalhe = new Usuario();
		usuarioStatus = new Usuario();
		usuarios = new UsuarioDAO().listarTodos();
	}

	public void voltar() {
		this.zerar();
	}

	// M�todo que v� o evento de upload do p:upload
	public void upload(FileUploadEvent event) {

		System.out.println("Entrou no m�todo upload");
		System.out.println("Nome do Arquivo: " + event.getFile().getFileName());

		// Pega o caminho completo do diret�rio
		String caminhoCompleto = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").toString()
				+ "\\resources\\img\\userPerfil\\";

		System.out.println("Caminho: " + caminhoCompleto);
		System.out.println("");

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
		}

		// Seta o caminho da imagem para ser salvo no banco baseado no caminho
		// est�tico criado acima
		usuario.setImagemPerfil(CAMINHOIMAGEM + event.getFile().getFileName());
		// System.out.println(usuario.getImagemPerfil());
	}
	
	public String cadastrarAreaLivre(){
		this.cadastrar();
		return "login.jsf?faces-redirect=true";
	}
	public void cadastrar() {

		System.out.println("Entrou no cadastro");

		// Parte da criptografia
		String textoEncriptado = "";
		if (editar) {
			textoEncriptado = usuario.getConta().getSenha();
		} else {
			EncriptAES aes = new EncriptAES();
			byte[] textoEmBytesEncriptados;
			try {

				textoEmBytesEncriptados = aes.encrypt(usuario.getConta().getSenha(), EncriptAES.getChaveEncriptacao());
				textoEncriptado = aes.byteParaString(textoEmBytesEncriptados);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		usuario.getConta().setSenha(textoEncriptado);
		usuario.getConta().setPerfil(Perfil.USUARIO);
		usuario.getConta().setDataCadastro(new Date());

		new UsuarioDAO().cadastrar(usuario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Salvo com sucesso"));
		this.zerar();
	}

	public void editar(Usuario usuario) {
		this.usuario = new UsuarioDAO().buscarPorId(usuario.getId());
		this.editar = true;
		rendered = false;
	}

	public void inverterAtividade(boolean status) {

		usuarioStatus.getConta().setAtivo(!status);

		new UsuarioDAO().cadastrar(usuarioStatus);
		this.zerar();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Status alterado com sucesso"));

	}

	public void preparaStatus(Usuario usuario) {
		this.usuarioStatus = usuario;
	}

	public void detalhes(Usuario usuario) {
		this.usuarioDetalhe = new UsuarioDAO().buscarPorId(usuario.getId());
	}

	public void novoUsuario() {
		rendered = false;
	}

	public String pegarImagem(Usuario usuario) {

		if (usuario.getImagemPerfil() == null || usuario.getImagemPerfil().equals("")) {
			return CAMINHOIMAGEM + "user.png";
		} else {
			return usuario.getImagemPerfil();
		}

	}

	public void zerar() {
		rendered = true;
		editar = false;
		usuario = new Usuario();
		usuario.setConta(new Conta());
		usuario.setEndereco(new Endereco());
		usuario.getEndereco().setCidade(new Cidade());
		usuario.getEndereco().getCidade().setEstado(new Estado());
		usuarioDetalhe = new Usuario();
		usuarioStatus = new Usuario();
		usuarios = new UsuarioDAO().listarTodos();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioDetalhe() {
		return usuarioDetalhe;
	}

	public void setUsuarioDetalhe(Usuario usuarioDetalhe) {
		this.usuarioDetalhe = usuarioDetalhe;
	}

	public Usuario getUsuarioStatus() {
		return usuarioStatus;
	}

	public void setUsuarioStatus(Usuario usuarioStatus) {
		this.usuarioStatus = usuarioStatus;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean redenred) {
		this.rendered = redenred;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

}
