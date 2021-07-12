package one.digital.innovation.pessoa.domain.repository.queire;

import java.util.List;

import one.digital.innovation.pessoa.domain.entity.Pessoa;
import one.digital.innovation.pessoa.domain.entity.filter.PessoaFiltro;

public interface PessoaRepositoryQueries {

	List<Pessoa> filtrar(PessoaFiltro filtro);
}
