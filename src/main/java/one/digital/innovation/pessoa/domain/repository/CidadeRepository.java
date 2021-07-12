package one.digital.innovation.pessoa.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digital.innovation.pessoa.domain.entity.Cidade;
import one.digital.innovation.pessoa.domain.repository.queire.CidadeRepositoryQueries;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> , CidadeRepositoryQueries{

	List<Cidade> findByNome(String nome);

}
