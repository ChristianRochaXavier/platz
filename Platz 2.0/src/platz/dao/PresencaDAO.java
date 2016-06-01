package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Presenca;

public class PresencaDAO {
	public void cadastrar(Presenca presenca) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(presenca);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Presenca> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Presenca> lista = entityManager.createQuery("from Presenca").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Presenca buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Presenca presenca = (Presenca) entityManager.createQuery("from Presenca where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return presenca;
	}

	// Excluir
	public void excluir(Presenca presenca) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		presenca = entityManager.merge(presenca);
		entityManager.remove(presenca);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
