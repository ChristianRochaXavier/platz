package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Evento;

public class EventoDAO {
	public void cadastrar(Evento evento) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(evento);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Evento> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Evento> lista = entityManager.createQuery("from Evento").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Evento buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Evento evento = (Evento) entityManager.createQuery("from Evento where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return evento;
	}

	// Excluir
	public void excluir(Evento evento) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		evento = entityManager.merge(evento);
		entityManager.remove(evento);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
