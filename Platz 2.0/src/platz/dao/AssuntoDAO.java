package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Assunto;



public class AssuntoDAO {
	public void cadastrar(Assunto assunto) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(assunto);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Assunto> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Assunto> lista = entityManager.createQuery("from Assunto").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Assunto buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Assunto assunto = (Assunto) entityManager
				.createQuery("from Assunto where id=:id").setParameter("id", id).getSingleResult();
		entityManager.close();

		return assunto;
	}

	// Excluir
	public void excluir(Assunto assunto) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		assunto = entityManager.merge(assunto);
		entityManager.remove(assunto);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
