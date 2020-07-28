package mx.artistas.digitales.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import mx.artistas.digitales.beans.UsuarioBean;
import mx.artistas.digitales.db.ConnectionWrapper;

public class AbcUsuarioDAO {
	
	
	private static final String WUERY_USUARIO = "SELECT  u.id_usuario, \r\n" + 
												"		u.nom_completo, \r\n" + 
												"        u.correo,\r\n" + 
												"        u.sexo, \r\n" + 
												"        u.pais,\r\n" + 
												"		r.nom_rol,\r\n" +
												"		r.id_rol\r\n" + 
												"FROM curso_java_web_db.usuarios_tab u,\r\n" + 
												"	  rol_tab r\r\n" + 
												"WHERE r.id_rol = u.id_rol";
	
	private static final String DELETE_USER 	= "DELETE FROM usuarios_tab WHERE id_usuario = ?";
	
	private static final String BUSCA_USUARIO	= "SELECT  u.id_usuario, \r\n" + 
			"														u.nom_completo, \r\n" + 
			"												        u.correo,\r\n" + 
			"												        u.sexo, \r\n" + 
			"												        u.pais,\r\n" + 
			"														r.nom_rol,\r\n" + 
			"														r.id_rol\r\n" + 
			"												FROM curso_java_web_db.usuarios_tab u,\r\n" + 
			"													  rol_tab r\r\n" + 
			"												WHERE r.id_rol = u.id_rol\r\n" + 
			"                                                AND   upper(u.nom_completo) like upper(?)";
	
	
	public List<UsuarioBean> getAllUsuario() {
		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
		Connection 			con =	 null;
		PreparedStatement 	psmt 	= null;
		ResultSet			rs		=	null;
		List<UsuarioBean>	listUsuarioBean	=	new ArrayList<>();
		UsuarioBean			usuarioBean	= null;
		try {
			con = connectionWrapper.getConection();
			psmt = con.prepareStatement(WUERY_USUARIO);
			rs = psmt.executeQuery();
			while(rs.next()) {
				usuarioBean = new UsuarioBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
						  rs.getString(5), rs.getString(6),rs.getInt(7));
				listUsuarioBean.add(usuarioBean);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				psmt.close();
				connectionWrapper.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return listUsuarioBean;
	}
	
	/**
	 * Elimina a un usuario de la tabla de usuarios
	 * @param idUsuario el idUsuario, identificador unico
	 * @return true si elimino correctamente, false si ocurrio un error
	 */
	public boolean eliminaUsuario(int idUsuario) {
		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
		Connection 			con 	= null;
		PreparedStatement 	psmt 	= null;
		boolean				exito	= false;
		try {
			con = connectionWrapper.getConection();
			psmt = con.prepareStatement(DELETE_USER);
			psmt.setInt(1, idUsuario);
			psmt.executeUpdate();
			exito = true;
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}finally {
			
			try {
				psmt.close();
				connectionWrapper.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return exito;
	}
	
	public List<UsuarioBean> buscaUsuario(String nombreBuscar) {
		ConnectionWrapper connectionWrapper = new ConnectionWrapper();
		Connection 			con 			= null;
		PreparedStatement 	psmt 			= null;
		ResultSet			rs				= null;
		List<UsuarioBean>	listUsuarioBean	= new ArrayList<>();
		UsuarioBean			usuarioBean		= null;
		
		try {
			con = connectionWrapper.getConection();
			psmt = con.prepareStatement(BUSCA_USUARIO);
			psmt.setString(1, "%" + nombreBuscar + "%");
			rs = psmt.executeQuery();
			while(rs.next()) {
				usuarioBean = new UsuarioBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
						  rs.getString(5), rs.getString(6),rs.getInt(7));
				listUsuarioBean.add(usuarioBean);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				psmt.close();
				connectionWrapper.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return listUsuarioBean;
	}

}
