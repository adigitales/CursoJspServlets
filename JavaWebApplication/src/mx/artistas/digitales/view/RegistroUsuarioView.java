package mx.artistas.digitales.view;

import java.util.List;

import mx.artistas.digitales.beans.RolesBean;
import mx.artistas.digitales.daos.RegistroUsuarioDAO;

public class RegistroUsuarioView {
	
	
	public List<RolesBean> getRoles() {
		RegistroUsuarioDAO regDAO = new RegistroUsuarioDAO();
		List<RolesBean> listRoles = regDAO.getRoles();
		return listRoles;
	}

}
