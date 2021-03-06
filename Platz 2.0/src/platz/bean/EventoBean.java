package platz.bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import platz.dao.CategoriaDAO;
import platz.dao.EventoDAO;
import platz.model.CategoriaEvento;
import platz.model.Cidade;
import platz.model.Empresa;
import platz.model.Endereco;
import platz.model.Estado;
import platz.model.Evento;

@ManagedBean
@SessionScoped
public class EventoBean {

	private Evento evento;
	private Evento eventoStatus;
	private Evento eventoExclusao;
	private Evento eventoEspecifico;
	private List<Evento> eventos;
	private CategoriaEvento categoriaPesquisa;
	private String stringPesquisa;
	private List<Evento> eventosPesquisa;
	private List<CategoriaEvento> categorias;
	private List<Evento> destaques;
	static final String CAMINHOIMAGEM = "/resources/img/eventos/";

	public EventoBean() {
		evento = new Evento();
		evento.setCategoriaEvento(new CategoriaEvento());
		evento.setEndereco(new Endereco());
		evento.setEmpresa(new Empresa());
		evento.getEndereco().setCidade(new Cidade());
		evento.getEndereco().getCidade().setEstado(new Estado());
		eventoStatus = new Evento();
		eventoExclusao = new Evento();
		eventoEspecifico = new Evento();
		eventos = new EventoDAO().listarTodos();
		categorias = new CategoriaDAO().listarTodos();
		destaques = new EventoDAO().listarDestaques();
	}

	public String pesquisaNomeCategoria() {
		eventosPesquisa = new EventoDAO().buscarporNomeCategoria(categoriaPesquisa, stringPesquisa);
		return "eventoBusca.jsf?faces-redirect=true";
	}

	public List<Evento> eventosPorEmpresa(Empresa empresa) {
		return new EventoDAO().listarPorEmpresa(empresa);
	}

	public void editar(Evento evento) {
		this.evento = evento;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("evento.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void zerar() {
		evento = new Evento();
		evento.setCategoriaEvento(new CategoriaEvento());
		evento.setEndereco(new Endereco());
		evento.setEmpresa(new Empresa());
		evento.getEndereco().setCidade(new Cidade());
		evento.getEndereco().getCidade().setEstado(new Estado());
		eventoStatus = new Evento();
		eventoExclusao = new Evento();
		eventos = new EventoDAO().listarTodos();
		categorias = new CategoriaDAO().listarTodos();
		destaques = new EventoDAO().listarDestaques();
		eventosPesquisa = new ArrayList<Evento>(); 
	}

	public void cadastrar(Empresa empresa) {

		this.evento.setEmpresa(empresa);
		new EventoDAO().cadastrar(evento);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento cadastrado com sucesso"));
		this.zerar();
	}

	// M�todo que v� o evento de upload do p:upload
	public void upload(FileUploadEvent event) {

		System.out.println("Entrou no m�todo upload");
		System.out.println("Nome do Arquivo: " + event.getFile().getFileName());

		// Pega o caminho completo do diret�rio
		String caminhoCompleto = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").toString()
				+ "\\resources\\img\\eventos\\";

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
		evento.setCaminhoImagem(CAMINHOIMAGEM + event.getFile().getFileName());
		// System.out.println(usuario.getImagemPerfil());
	}

	public List<Evento> listaPorCategoria(CategoriaEvento categoria) {

		return new EventoDAO().listarPorCategoria(categoria);
	}

	public String eventoEspecificoAreaLivre(Evento evento) {

		this.eventoEspecifico = evento;

		return "eventoEspecifico?faces-redirect=true";

	}

	public String pegarImagem(Evento evento) {
		if (evento.getCaminhoImagem() == null || evento.getCaminhoImagem().equals("")) {
			return CAMINHOIMAGEM + "evento.jpg";
		} else {
			return evento.getCaminhoImagem();
		}
	}

	public void alteraStatus() {

	}

	public void inverterAtividade() {
	}

	// Getters and Setters
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Evento getEventoStatus() {
		return eventoStatus;
	}

	public void setEventoStatus(Evento eventoStatus) {
		this.eventoStatus = eventoStatus;
	}

	public Evento getEventoExclusao() {
		return eventoExclusao;
	}

	public void setEventoExclusao(Evento eventoExclusao) {
		this.eventoExclusao = eventoExclusao;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<CategoriaEvento> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaEvento> categorias) {
		this.categorias = categorias;
	}

	public List<Evento> getDestaques() {
		return destaques;
	}

	public void setDestaques(List<Evento> destaques) {
		this.destaques = destaques;
	}

	public Evento getEventoEspecifico() {
		return eventoEspecifico;
	}

	public void setEventoEspecifico(Evento eventoEspecifico) {
		this.eventoEspecifico = eventoEspecifico;
	}

	public CategoriaEvento getCategoriaPesquisa() {
		return categoriaPesquisa;
	}

	public void setCategoriaPesquisa(CategoriaEvento categoriaPesquisa) {
		this.categoriaPesquisa = categoriaPesquisa;
	}

	public String getStringPesquisa() {
		return stringPesquisa;
	}

	public void setStringPesquisa(String stringPesquisa) {
		this.stringPesquisa = stringPesquisa;
	}

	public List<Evento> getEventosPesquisa() {
		return eventosPesquisa;
	}

	public void setEventosPesquisa(List<Evento> eventosPesquisa) {
		this.eventosPesquisa = eventosPesquisa;
	}
	

}
