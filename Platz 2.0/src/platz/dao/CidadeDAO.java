package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Cidade;

public class CidadeDAO {
	@SuppressWarnings("unchecked")
	public List<Cidade> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Cidade> lista = entityManager.createQuery("from Cidade").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Cidade buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Cidade cidade = (Cidade) entityManager.createQuery("from Cidade where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return cidade;
	}

	public Cidade buscarPorNome(String nomeCidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		Cidade cidade = (Cidade) entityManager.createQuery("from Cidade where nome=:nomeCidade")
				.setParameter("nomeCidade", nomeCidade);
		entityManager.close();
		return cidade;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscaPorEstado(Integer idEstado) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Cidade> lista = entityManager.createQuery("from Cidade where estado =:idEstado")
				.setParameter("idEstado", idEstado).getResultList();
		entityManager.close();
		return lista;
	}
}
