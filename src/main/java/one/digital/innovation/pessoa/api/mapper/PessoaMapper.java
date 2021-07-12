package one.digital.innovation.pessoa.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import one.digital.innovation.pessoa.api.dto.input.PessoaInput;
import one.digital.innovation.pessoa.api.dto.model.PessoaModel;
import one.digital.innovation.pessoa.domain.entity.Pessoa;

@Component
public class PessoaMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PessoaModel toModel(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaModel.class);
	}

	public List<PessoaModel> toCollectionModel(List<Pessoa> pessoas) {
		return pessoas.stream().map(pessoa -> toModel(pessoa)).collect(Collectors.toList());
	}

	public Pessoa toDomain(PessoaInput input) {
		return modelMapper.map(input, Pessoa.class);
	}

	public void copyToDomainObject(PessoaInput input, Pessoa pessoa) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// br.com.apssystem.algafood-api.domain.model.Cidade was altered from 1 to 2
		modelMapper.map(input, pessoa);
	}
}
