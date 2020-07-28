package mx.artistas.digitales.beans;

import java.io.Serializable;

public class FormularioBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomCompleto;
	private String correo;
	private String password;
	private String sexo;
	private String pais;
	
	
	public FormularioBean(String nomCompleto, String correo, String password, String sexo, String pais) {
		super();
		this.nomCompleto = nomCompleto;
		this.correo = correo;
		this.password = password;
		this.sexo = sexo;
		this.pais = pais;
	}
	
	public String getNomCompleto() {
		return nomCompleto;
	}
	public void setNomCompleto(String nomCompleto) {
		this.nomCompleto = nomCompleto;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
	

}
