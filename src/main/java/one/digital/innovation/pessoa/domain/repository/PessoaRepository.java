package one.digital.innovation.pessoa.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import one.digital.innovation.pessoa.domain.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	List<Pessoa> findByNome(String nome);
	
	@Query("SELECT bean FROM Pessoa bean JOIN bean.telefones tele WHERE tele.ddd = :ddd AND tele.numero = :numero")
	Optional<Pessoa> findByTelefoneDddAndTelefoneNumero(@Param("ddd") String ddd, @Param("numero") String numero);

	Optional<Pessoa> findByCpf(String string);

	Optional<Pessoa> findById(String string);

}
