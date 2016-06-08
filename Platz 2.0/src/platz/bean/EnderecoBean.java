package platz.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import platz.dao.EnderecoDAO;
import platz.model.Endereco;

@ManagedBean
@SessionScoped
public class EnderecoBean {
	private Endereco endereco;
	private Endereco enderecoDetalhe;
	private List<Endereco> enderecos;
	public EnderecoBean() {
		endereco = new Endereco();
		enderecoDetalhe = new Endereco();
		enderecos = new EnderecoDAO().listarTodos();
	}
	public void detalhe( Endereco endereco){
		this.enderecoDetalhe = endereco;
	}
	
	public Endereco getEnderecoDetalhe() {
		return enderecoDetalhe;
	}
	public void setEnderecoDetalhe(Endereco enderecoDetalhe) {
		this.enderecoDetalhe = enderecoDetalhe;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
