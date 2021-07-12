package one.digital.innovation.pessoa.domain.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import one.digital.innovation.pessoa.domain.entity.Cidade;
import one.digital.innovation.pessoa.domain.repository.queire.CidadeRepositoryQueries;

@Repository
public class CidadeRepositoryImpl implements CidadeRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Cidade consultarPorNomeESilga(Cidade cidade) {
		try {
			String jpql = "from Cidade c where c.nome = :nome and c.estado.id = :id  ";
			return manager.createQuery(jpql, Cidade.class).setParameter("nome", cidade.getNome())
					.setParameter("id", cidade.getEstado().getId()).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Cidade> listarCidadesPorEstado(String sigla) {
		String jpql = "from Cidade c where c.estado.sigla = :sigla  ";
		return manager.createQuery(jpql, Cidade.class).setParameter("id", sigla).getResultList();
	}

}
