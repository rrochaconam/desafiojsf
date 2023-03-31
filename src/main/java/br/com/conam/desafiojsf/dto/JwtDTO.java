package br.com.conam.desafiojsf.dto;

public class JwtDTO {

	private String token;
	private Long idUsuario;

	public JwtDTO(String token, Long idUsuario) {
		this.token = token;
		this.idUsuario = idUsuario;
	}

	public String getToken() {
		return token;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

}