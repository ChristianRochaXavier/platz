package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Avaliacoes;

public class AvaliacoesDAO {
	public void cadastrar(Avaliacoes avaliacao) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(avaliacao);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Avaliacoes> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Avaliacoes> lista = entityManager.createQuery("from Avaliacoes").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Avaliacoes buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Avaliacoes avaliacao = (Avaliacoes) entityManager.createQuery("from Avaliacoes where id=:id")
				.setParameter("id", id).getSingleResult();
		entityManager.close();

		return avaliacao;
	}

	// Excluir
	public void excluir(Avaliacoes avaliacao) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		avaliacao = entityManager.merge(avaliacao);
		entityManager.remove(avaliacao);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}