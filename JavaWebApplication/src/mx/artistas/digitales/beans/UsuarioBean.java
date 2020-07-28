package mx.artistas.digitales.beans;

import java.io.Serializable;

public class UsuarioBean implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	private int idUsuario;
	private String nomCompleto;
	private String correo;
	private String sexo;
	private String pais;
	private int idRol;
	private String nomRol;
	private String pass;
	
	public UsuarioBean() {
		
	}

	public UsuarioBean(int idUsuario,String nomCompleto, String correo, String sexo, String pais, int idRol, String pass) {
		super();
		this.nomCompleto = nomCompleto;
		this.correo = correo;
		this.sexo = sexo;
		this.pais = pais;
		this.idRol = idRol;
		this.pass = pass;
		this.idUsuario = idUsuario;
	}
	
	public UsuarioBean(String nomCompleto, String correo, String sexo, String pais, int idRol, String pass) {
		super();
		this.nomCompleto = nomCompleto;
		this.correo = correo;
		this.sexo = sexo;
		this.pais = pais;
		this.idRol = idRol;
		this.pass = pass;
	}



	public UsuarioBean(int idUsuario, String nomCompleto, String correo, String sexo, String pais, String nomRol,int idRol) {
		super();
		this.idUsuario = idUsuario;
		this.nomCompleto = nomCompleto;
		this.correo = correo;
		this.sexo = sexo;
		this.pais = pais;
		this.nomRol = nomRol;
		this.idRol = idRol;
	}
	
	
	
	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public int getIdRol() {
		return idRol;
	}



	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}



	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
	public String getNomRol() {
		return nomRol;
	}
	public void setNomRol(String nomRol) {
		this.nomRol = nomRol;
	}
	
	
	

}
