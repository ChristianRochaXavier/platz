package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Usuario;

public class UsuarioDAO {
	
	public void cadastrar(Usuario usuario) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Usuario> lista = entityManager.createQuery("from Usuario").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Usuario buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Usuario usuario = (Usuario) entityManager.createQuery("from Usuario where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return usuario;
	}

	// Excluir
	public void excluir(Usuario usuario) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		usuario = entityManager.merge(usuario);
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
