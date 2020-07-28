package mx.artistas.digitales.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import mx.artistas.digitales.beans.FormularioBean;
import mx.artistas.digitales.beans.RolesBean;
import mx.artistas.digitales.beans.UsuarioBean;
import mx.artistas.digitales.db.ConnectionWrapper;

public class RegistroUsuarioDAO {
	
	private static final String QUERY_ROL = "SELECT id_rol,\r\n" + 
									"	   nom_rol\r\n" + 
									"FROM curso_java_web_db.rol_tab";
	
	private final static String INSERT_USER = "INSERT INTO curso_java_web_db.usuarios_tab\r\n" + 
			"															(nom_completo,\r\n" + 
			"															correo,\r\n" + 
			"															password,\r\n" + 
			"															sexo,\r\n" + 
			"															pais,\r\n" + 
			"															fec_creation,\r\n" + 
			"															creation_by,\r\n" + 
			"															last_update_date,\r\n" + 
			"															update_by,\r\n" +
			"															id_rol)"+				
			"															VALUES\r\n" + 
			"															(?,?,md5(?),?,?,sysdate(),0,sysdate(),0,?)";
	
	private final static String UPDATE_USER = "UPDATE curso_java_web_db.usuarios_tab\r\n" + 
												"SET\r\n" + 
												"nom_completo = ?,\r\n" + 
												"correo = ?,\r\n" + 
												"sexo = ?,\r\n" + 
												"pais = ?,\r\n" + 
												"id_rol = ?,\r\n" + 
												"last_update_date = sysdate(),\r\n" + 
												"update_by = ?\r\n" + 
												"WHERE id_usuario = ?";
	private final static String UPDATE_USER_WITH_PASS = "UPDATE curso_java_web_db.usuarios_tab\r\n" + 
														"SET\r\n" + 
														"nom_completo = ?,\r\n" + 
														"correo = ?,\r\n" + 
														"sexo = ?,\r\n" + 
														"pais = ?,\r\n" + 
														"id_rol = ?,\r\n" + 
														"last_update_date = sysdate(),\r\n" + 
														"update_by = ?,\r\n" +
														"password = MD5(?)\r\n" + 
														"WHERE id_usuario = ?";
	
	
	/**
	 * Metodo que actualiza a un usuario
	 * @param usuarioBean bean de usuario
	 * @return true si actualizo correctamente, false si ocurrio un error en la actualizacion
	 */
	public boolean editUser(UsuarioBean usuarioBean,int systemUser) {
		ConnectionWrapper 	connectionWrapper 	= new ConnectionWrapper();
		Connection 			con 				= null;
		PreparedStatement 	psmt 				= null;
		boolean 			paso 				= false;
		try {
			con = connectionWrapper.getConection();
			psmt = con.prepareStatement(usuarioBean.getPass().isEmpty()?UPDATE_USER:UPDATE_USER_WITH_PASS);
			psmt.setString(1,usuarioBean.getNomCompleto());
			psmt.setString(2,usuarioBean.getCorreo());
			psmt.setString(3,usuarioBean.getSexo());
			psmt.setString(4,usuarioBean.getPais());
			psmt.setInt(5, usuarioBean.getIdRol());
			psmt.setInt(6, systemUser);
			if(usuarioBean.getPass().isEmpty()) {
				psmt.setInt(7, usuarioBean.getIdUsuario());
			}else {
				psmt.setString(7, usuarioBean.getPass());
				psmt.setInt(8, usuarioBean.getIdUsuario());
				
			}
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
	
	
	/***
	 * Metodo que inserta un usuario
	 * @param formularioBean bean que contiene los datos del usuario
	 * @return true si inserto correctamente, false si no inserto
	 */
	public boolean insertUser(UsuarioBean usuarioBean) {
		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
		Connection 			con 	= null;
		PreparedStatement psmt = null;
		boolean paso = false;
		try {
			con = connectionWrapper.getConection();
			psmt = con.prepareStatement(INSERT_USER);
			psmt.setString(1,usuarioBean.getNomCompleto());
			psmt.setString(2,usuarioBean.getCorreo());
			psmt.setString(3,usuarioBean.getPass());
			psmt.setString(4,usuarioBean.getSexo());
			psmt.setString(5,usuarioBean.getPais());
			psmt.setInt(6, usuarioBean.getIdRol());
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
	
	
	
	public List<RolesBean> getRoles() {
		Connection 			con 	= null;
		PreparedStatement 	psmt 	= null;
		ResultSet 			rs 		= null;
		List<RolesBean>		listRoles	=	new ArrayList<>();
		ConnectionWrapper conWrapper = new ConnectionWrapper();
		try {
			con = conWrapper.getConection();
			psmt = con.prepareStatement(QUERY_ROL);
			rs = psmt.executeQuery();
			while(rs.next()) {
				listRoles.add(new RolesBean (rs.getInt(1),rs.getString(2)));
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				psmt.close();
				conWrapper.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return listRoles;
		
	}

}
