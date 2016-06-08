package platz.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String evento;
	private boolean destaque = false;
	private String detalhes;
	private Date dataInicio;
	private Date dataTermino;
	private Integer lotacaoMin;
	private Integer lotacaoMax;
	private Double preco;
	private String caminhoImagem;
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;
	@ManyToOne
	@JoinColumn(name = "idCategoriaEvento")
	private CategoriaEvento categoriaEvento;
	@OneToOne
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;

	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotEmpty(message = "O nome do evento deve ser informado")
	@Length(min = 1, max = 50, message = "O nome do evento pode ter no máximo 50 caracteres")
	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public boolean isDestaque() {
		return destaque;
	}

	public void setDestaque(boolean destaque) {
		this.destaque = destaque;
	}

	@NotEmpty(message = "Os detalhes do evento devem ser informados")
	@Length(min = 5, max = 500, message = "Os detalhes do evento devem ter entre 5 e 500 caracteres")
	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "A data de início deve ser informada")
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "A date de término deve ser informada")
	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Integer getLotacaoMin() {
		return lotacaoMin;
	}

	public void setLotacaoMin(Integer lotacaoMin) {
		this.lotacaoMin = lotacaoMin;
	}

	public Integer getLotacaoMax() {
		return lotacaoMax;
	}

	public void setLotacaoMax(Integer lotacaoMax) {
		this.lotacaoMax = lotacaoMax;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	@NotNull
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@NotNull
	public CategoriaEvento getCategoriaEvento() {
		return categoriaEvento;
	}

	public void setCategoriaEvento(CategoriaEvento categoriaEvento) {
		this.categoriaEvento = categoriaEvento;
	}

	@NotNull
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
