package br.com.conam.desafiojsf.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.conam.desafiojsf.util.BaseEntity;

public class OperadorDTO implements BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5176879398376515231L;
	
	private Long id;
	private String nome;
	private String chave;
	private String tenant;
	private String email;
	private String senha;
	private String cpf;
	private Date ultimoAcesso;
	private EntidadeDTO entidade;
	private List<ModuloDTO> modulos = new ArrayList<>();
	private List<EntidadeDTO> entidades = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	
	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public EntidadeDTO getEntidade() {
		return entidade;
	}

	public void setEntidade(EntidadeDTO entidade) {
		this.entidade = entidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<ModuloDTO> getModulos() {
		return modulos;
	}

	public void setModulos(List<ModuloDTO> modulos) {
		this.modulos = modulos;
	}

	public List<EntidadeDTO> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadeDTO> entidades) {
		this.entidades = entidades;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		return defaultHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return defaultEquals(obj);
	}

	public void setId(final Long id) {
        this.id = id;
    }
    
    @Override
    public Long getId() {
        return id;
    }

}
