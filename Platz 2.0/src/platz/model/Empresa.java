package platz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@OneToOne
	@JoinColumn(name="idConta")
	private Conta conta;
	@OneToOne
	@JoinColumn(name="idEndereco")
	private Endereco endereco;
	private String cnpj;
	private String nomeFantasia;
	private String razaoSocial;
	private String telefone;
	private String telefone2;
	private String imagemPerfil;
	
	//Getters and Setters

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotNull
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	@NotNull
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	@NotEmpty(message = "O CNPJ deve ser informado")
	@Length(min = 14, max = 18, message = "CNPJ inválido")
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@NotEmpty(message = "O nome da empresa deve ser informado")
	@Length(min = 3, max = 50, message = "O nome da empresa deve ter entre 3 e 50 caracteres")
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@NotEmpty(message = "A razão social deve ser informado")
	@Length(min = 3, max = 75, message = "A razão social deve ter entre 3 e 75 caracteres")
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	@NotEmpty(message = "O telefone deve ser informado")
	@Length(min = 9, max = 10, message = "Telefone inválido")
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getImagemPerfil() {
		return imagemPerfil;
	}
	public void setImagemPerfil(String imagemPerfil) {
		this.imagemPerfil = imagemPerfil;
	}
		
}
