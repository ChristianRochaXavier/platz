package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Postagem;

public class PostagemDAO {
	public void cadastrar(Postagem postagem) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(postagem);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Postagem> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Postagem> lista = entityManager.createQuery("from Postagem").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Postagem buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Postagem postagem = (Postagem) entityManager.createQuery("from Postagem where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return postagem;
	}

	// Excluir
	public void excluir(Postagem postagem) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		postagem = entityManager.merge(postagem);
		entityManager.remove(postagem);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
