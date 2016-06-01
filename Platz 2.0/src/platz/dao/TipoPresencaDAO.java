package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.TipoPresenca;

public class TipoPresencaDAO {
	public void cadastrar(TipoPresenca tipoPresenca) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(tipoPresenca);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<TipoPresenca> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<TipoPresenca> lista = entityManager.createQuery("from TipoPresenca").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public TipoPresenca buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		TipoPresenca tipoPresenca = (TipoPresenca) entityManager.createQuery("from TipoPresenca where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return tipoPresenca;
	}

	// Excluir
	public void excluir(TipoPresenca tipoPresenca) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		tipoPresenca = entityManager.merge(tipoPresenca);
		entityManager.remove(tipoPresenca);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
