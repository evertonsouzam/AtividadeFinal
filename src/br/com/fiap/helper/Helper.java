package br.com.fiap.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.entity.Multa;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Veiculo;

public class Helper {
	private EntityManager em;

	public Helper(EntityManager em) {
		this.em = em;
	}

	public void salvar(Pessoa pessoa) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(pessoa);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			
		}
	}

	public void salvar(Veiculo veiculo) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(veiculo);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			
		}
	}
	
	public void salvar(Multa multa) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(multa);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			
		}
	}
	
	// JPQL: Usando Query
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarPessoas() {
		Query query = em.createQuery("select p from Pessoa p");
		return query.getResultList();
	}

	public Pessoa buscarPessoa(int idPessoa){
		Query query = em.createQuery("select p from Pessoa p where idpessoa = :idpessoa");
		query.setParameter("idpessoa", idPessoa);
		Pessoa p = (Pessoa)query.getSingleResult();
		return p;
		}

	// JPQL: usando NamedQuery
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarTodasPessoas() {
		Query query = em.createNamedQuery("Pessoa.findAll");
		return query.getResultList();
	}
}
