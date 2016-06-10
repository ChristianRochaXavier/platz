package platz.bean;

import java.io.FileOutputStream;
import java.io.IOException;
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
	private Evento eventoDetalhe;
	private Evento eventoExclusao;
	private List<Evento> eventos;
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
		eventoDetalhe = new Evento();
		eventoExclusao = new Evento();
		eventos = new EventoDAO().listarTodos();
		categorias = new CategoriaDAO().listarTodos();
		destaques = new EventoDAO().listarDestaques();
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
		eventoDetalhe = new Evento();
		eventoExclusao = new Evento();
		eventos = new EventoDAO().listarTodos();
		categorias = new CategoriaDAO().listarTodos();
	}

	public void cadastrar(Empresa empresa) {

		this.evento.setEmpresa(empresa);
		new EventoDAO().cadastrar(evento);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento cadastrado com sucesso"));
		this.zerar();
	}

	// Método que vê o evento de upload do p:upload
	public void upload(FileUploadEvent event) {

		System.out.println("Entrou no método upload");
		System.out.println("Nome do Arquivo: " + event.getFile().getFileName());

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

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload feito com sucesso"));

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		// Seta o caminho da imagem para ser salvo no banco baseado no caminho
		// estático criado acima
		evento.setCaminhoImagem(CAMINHOIMAGEM + event.getFile().getFileName());
		// System.out.println(usuario.getImagemPerfil());
	}

	public void inverterAtividade() {
	}

	public List<Evento> listaPorCategoria(CategoriaEvento categoria) {

		List<Evento> lista = new EventoDAO().listarPorCategoria(categoria);

		return lista;

	}

	public void detalhes(Evento evento) {

		this.eventoDetalhe = evento;

	}

	public void alteraStatus() {

	}

	public void pegarImagem() {

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

	public Evento getEventoDetalhe() {
		return eventoDetalhe;
	}

	public void setEventoDetalhe(Evento eventoDetalhe) {
		this.eventoDetalhe = eventoDetalhe;
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

}
