package platz.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import platz.dao.AvaliacoesDAO;
import platz.model.Avaliacoes;

@ManagedBean
@SessionScoped
public class AvaliacoesBean {
	private Avaliacoes avaliacao;
	private List<Avaliacoes> avaliacoes = new AvaliacoesDAO().listarTodos();

	public AvaliacoesBean() {
		avaliacao = new Avaliacoes();
		avaliacoes = new AvaliacoesDAO().listarTodos();
	}

	public Avaliacoes getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacoes avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Avaliacoes> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacoes> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

}
