package platz.dao;

import java.util.List;

import javax.persistence.EntityManager;

import platz.model.Conta;
import platz.model.Empresa;


public class EmpresaDAO {
	public void cadastrar(Empresa empresa) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(empresa);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> listarTodos() {

		EntityManager entityManager = JPAUtil.getEntityManager();
		//List<Empresa> lista = entityManager.createQuery("from Empresa").getResultList();
		List<Empresa> lista = entityManager.createQuery("select new platz.model.Empresa(id, nomeFantasia, razaoSocial, cnpj, conta.email) from Empresa").getResultList();
		entityManager.close();

		return lista;
	}

	// Buscar por id
	public Empresa buscarPorId(Integer id) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Empresa empresa= (Empresa) entityManager.createQuery("from Empresa where id=:id")
				.setParameter("id", id).getSingleResult();
		entityManager.close();

		return empresa;
	}

	// Buscar por conta
	public Empresa buscarPorConta(Conta conta) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		Empresa empresa = (Empresa) entityManager.createQuery("from Empresa where conta=:conta").setParameter("conta", conta).getSingleResult();
		entityManager.close();

		return empresa;
	}

	// Excluir
	public void excluir(Empresa empresa) {

		EntityManager entityManager = JPAUtil.getEntityManager();

		entityManager.getTransaction().begin();
		empresa = entityManager.merge(empresa);
		entityManager.remove(empresa);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}