package platz.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;

import platz.model.CategoriaEvento;
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
	static final String CAMINHOIMAGEM = "/resources/img/eventos/";
	
	public void editar(Evento evento){}
	public void zerar(){}
	public void cadastrar(){}
	public void upload(FileUploadEvent event){}
	public void inverterAtividade(){}
	public void detalhes(){}
	public void alteraStatus(){}
	public void pegarImagem(){}
	
	
	public EventoBean() {

	}

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

}
