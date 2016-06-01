package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.CategoriaEvento;

public class CategoriaDAO {

	public void cadastrar(CategoriaEvento categoria) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(categoria);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<CategoriaEvento> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<CategoriaEvento> lista = entityManager.createQuery("from CategoriaEvento").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public CategoriaEvento buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		CategoriaEvento categoriaEvento = (CategoriaEvento) entityManager
				.createQuery("from CategoriaEvento where id=:id").setParameter("id", id).getSingleResult();
		entityManager.close();

		return categoriaEvento;
	}

	// Excluir
	public void excluir(CategoriaEvento categoria) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		categoria = entityManager.merge(categoria);
		entityManager.remove(categoria);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
