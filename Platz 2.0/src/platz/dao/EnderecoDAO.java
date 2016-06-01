package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Cidade;
import platz.model.Endereco;

public class EnderecoDAO {
	public void cadastrar(Endereco endereco, Cidade cidade) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(cidade);
		entityManager.merge(endereco);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Endereco> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		List<Endereco> lista = entityManager.createQuery("from Endereco").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Endereco buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Endereco endereco = (Endereco) entityManager.createQuery("from Endereco where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return endereco;
	}

	// Excluir
	public void excluir(Endereco endereco) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		endereco = entityManager.merge(endereco);
		entityManager.remove(endereco);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
