package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Mensagem;

public class MensagemDAO {
	public void cadastrar(Mensagem mensagem) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(mensagem);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Mensagem> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Mensagem> lista = entityManager.createQuery("from Mensagem").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Mensagem buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Mensagem mensagem = (Mensagem) entityManager.createQuery("from Mensagem where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return mensagem;
	}

	// Excluir
	public void excluir(Mensagem mensagem) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		mensagem = entityManager.merge(mensagem);
		entityManager.remove(mensagem);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
