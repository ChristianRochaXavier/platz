package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import platz.model.Conta;

public class ContaDAO {

	public Conta getConta(String Login, String Senha) {

		try {

			EntityManager entityManager = JPAUtil.getEntityManager();

			Conta conta = (Conta) entityManager
					.createQuery("SELECT c from Conta c where c.login = :Login and c.senha = :Senha")
					.setParameter("Login", Login).setParameter("Senha", Senha).getSingleResult();

			entityManager.close();

			return conta;

		} catch (NoResultException e) {

			return null;
		}
	}

	public void cadastrar(Conta conta) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(conta);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Conta> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		//List<Conta> lista = entityManager.createQuery("from Conta").getResultList();
		List<Conta> lista = entityManager.createQuery("select new platz.model.Conta(id, login, perfil, ativo) from Conta").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Conta buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Conta conta = (Conta) entityManager.createQuery("from Conta where id=:id").setParameter("id", id)
				.getSingleResult();
		entityManager.close();

		return conta;
	}

	// Excluir
	public void excluir(Conta conta) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		conta = entityManager.merge(conta);
		entityManager.remove(conta);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
