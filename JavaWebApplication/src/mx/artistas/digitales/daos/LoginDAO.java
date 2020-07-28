package mx.artistas.digitales.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import mx.artistas.digitales.beans.UsuarioBean;
import mx.artistas.digitales.db.ConnectionWrapper;

public class LoginDAO {
	
	private static final String QUERY_LOGIN = "SELECT  u.id_usuario, \r\n" + 
										"		u.nom_completo, \r\n" + 
										"        u.correo,\r\n" + 
										"        u.sexo, \r\n" + 
										"        u.pais,\r\n" + 
										"		r.nom_rol,\r\n" +
										"		r.id_rol\r\n" +
										"FROM curso_java_web_db.usuarios_tab u,\r\n" + 
										"	  rol_tab r\r\n" + 
										"WHERE r.id_rol = u.id_rol\r\n" + 
										"AND u.correo 	= ?\r\n" + 
										"AND   u.password = md5(?)";
	Connection con;
	
	public UsuarioBean getuserLogin(String correo, String pass) {
		ConnectionWrapper conWrapper = new ConnectionWrapper();
		PreparedStatement 	psmt = null;
		ResultSet 			rs = null;
		int					existe = 0;
		UsuarioBean usuarioBean = null;
		try {
			con = conWrapper.getConection();			
			psmt = con.prepareStatement(QUERY_LOGIN);
			psmt.setString(1, correo);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				usuarioBean = new UsuarioBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
											  rs.getString(5), rs.getString(6),rs.getInt(7));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				psmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return usuarioBean;
		
		
	}

}
