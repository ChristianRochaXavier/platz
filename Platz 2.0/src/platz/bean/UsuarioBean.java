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
	private boolean rendered = true;//caso true mostra a lista, caso false o formulario

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

	public void voltar() {		
		System.out.println(rendered);
		rendered = true;
	}

	// Método que vê o evento de upload do p:upload
	public void upload(FileUploadEvent event) {

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

			// System.out.println("Upload OK");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		// Seta o caminho da imagem para ser salvo no banco baseado no caminho
		// estático criado acima
		usuario.setImagemPerfil(CAMINHOIMAGEM + event.getFile().getFileName());
		System.out.println(usuario.getImagemPerfil());
		System.out.println("");

	}

	public void cadastrar() {

		System.out.println("Entrou no cadastro");

		// Parte da criptografia
		String textoEncriptado = "";
		EncriptAES aes = new EncriptAES();
		byte[] textoEmBytesEncriptados;
		try {

			textoEmBytesEncriptados = aes.encrypt(usuario.getConta().getSenha(), EncriptAES.getChaveEncriptacao());
			textoEncriptado = aes.byteParaString(textoEmBytesEncriptados);

		} catch (Exception e) {
			e.printStackTrace();
		}

		usuario.getConta().setSenha(textoEncriptado);
		usuario.getConta().setPerfil(Perfil.USUARIO);
		usuario.getConta().setDataCadastro(new Date());

		new UsuarioDAO().cadastrar(usuario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Salvo com sucesso"));
		this.zerar();
	}

	public void editar(Usuario usuario) {
		this.usuario = usuario;
		rendered = false;
	}

	public void alteraStatus() {
		usuarioStatus.getConta().setAtivo(!usuario.getConta().isAtivo());
	}

	public void preparaStatus(Usuario usuario) {
		this.usuarioStatus = usuario;
	}

	public void detalhes(Usuario usuario) {
		this.usuarioDetalhe = usuario;
	}

	public void novoUsuario() {		
		rendered = false;		
	}

	public void zerar() {
		rendered = true;
		usuario = new Usuario();
		usuario.setConta(new Conta());
		usuario.setEndereco(new Endereco());
		usuario.getEndereco().setCidade(new Cidade());
		usuario.getEndereco().getCidade().setEstado(new Estado());
		usuarioDetalhe = new Usuario();
		usuarioStatus = new Usuario();
		usuarios = new UsuarioDAO().listarTodos();
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean redenred) {
		this.rendered = redenred;
	}

}
