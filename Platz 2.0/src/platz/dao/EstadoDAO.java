package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Estado;

public class EstadoDAO {
	@SuppressWarnings("unchecked")
	public List<Estado> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Estado> lista = entityManager.createQuery("from Estado").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Estado buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Estado estado = (Estado) entityManager.createQuery("from Estado where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return estado;
	}
	public Estado buscarPorNome(String nomeEstado){
		EntityManager entityManager = JPAUtil.getEntityManager();
		Estado estado = (Estado) entityManager.createQuery("from Estado where nome=:nomeEstado").setParameter("nomeEstado", nomeEstado);
		entityManager.close();
		return estado;
	}
}
