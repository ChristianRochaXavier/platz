package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.CategoriaEvento;
import platz.model.Empresa;
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

	@SuppressWarnings("unchecked")
	public List<Evento> listarPorCategoria(CategoriaEvento categoria) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Evento> lista = entityManager.createQuery("select e from Evento e where e.categoriaEvento = :categoria")
				.setParameter("categoria", categoria).getResultList();
		entityManager.close();

		return lista;

	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> listarDestaques(){
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Evento> lista = entityManager.createQuery("select e from Evento e where e.destaque = true").getResultList();
		entityManager.close();

		return lista;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> listarPorEmpresa(Empresa empresa){
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Evento> lista = entityManager.createQuery("select e from Evento e where e.empresa = :empresa")
				.setParameter("empresa", empresa).getResultList();
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
