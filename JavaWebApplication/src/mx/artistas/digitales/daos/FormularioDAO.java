package mx.artistas.digitales.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

import mx.artistas.digitales.beans.FormularioBean;
import mx.artistas.digitales.db.ConnectionWrapper;

public class FormularioDAO {
	
	Connection con;
	
	private final static String INSERT_USER = "INSERT INTO curso_java_web_db.usuarios_tab\r\n" + 
			"															(nom_completo,\r\n" + 
			"															correo,\r\n" + 
			"															password,\r\n" + 
			"															sexo,\r\n" + 
			"															pais,\r\n" + 
			"															fec_creation,\r\n" + 
			"															creation_by,\r\n" + 
			"															last_update_date,\r\n" + 
			"															update_by)\r\n" + 
			"															VALUES\r\n" + 
			"															(?,?,md5(?),?,?,sysdate(),0,sysdate(),0)";
	
	
	/***
	 * Metodo que inserta un usuario
	 * @param formularioBean bean que contiene los datos del usuario
	 * @return true si inserto correctamente, false si no inserto
	 */
	public boolean insertUser(FormularioBean formularioBean) {
		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
		
		PreparedStatement psmt = null;
		boolean paso = false;
		try {
			con = connectionWrapper.getConection();
			psmt = con.prepareStatement(INSERT_USER);
			psmt.setString(1,formularioBean.getNomCompleto());
			psmt.setString(2,formularioBean.getCorreo());
			psmt.setString(3,formularioBean.getPassword());
			psmt.setString(4,formularioBean.getSexo());
			psmt.setString(5,formularioBean.getPais());
			psmt.executeUpdate();			
			paso = true;
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}finally {
			try {
				psmt.close();
				con.close();
				connectionWrapper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return paso;
	}
	
	
	
}
