package one.digital.innovation.pessoa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import one.digital.innovation.pessoa.domain.entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	Optional<Estado> findBySigla(String sigla);

}
