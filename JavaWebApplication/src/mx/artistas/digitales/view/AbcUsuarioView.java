package mx.artistas.digitales.view;

import java.util.List;

import mx.artistas.digitales.beans.UsuarioBean;
import mx.artistas.digitales.daos.AbcUsuarioDAO;

public class AbcUsuarioView {
	
	
	public List<UsuarioBean> getAllUSer() {
		AbcUsuarioDAO userDao = new AbcUsuarioDAO();
		List<UsuarioBean> listUsers = userDao.getAllUsuario();
		return listUsers;
	}
	
	public List<UsuarioBean> getUserFilter(String nomBuscar) {
		AbcUsuarioDAO userDao = new AbcUsuarioDAO();
		List<UsuarioBean> listUsers = userDao.buscaUsuario(nomBuscar);
		return listUsers;
	}

}
