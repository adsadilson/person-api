package one.digital.innovation.pessoa.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import one.digital.innovation.pessoa.api.dto.input.EnderecoInput;
import one.digital.innovation.pessoa.api.dto.model.EnderecoModel;
import one.digital.innovation.pessoa.domain.entity.Cidade;
import one.digital.innovation.pessoa.domain.entity.Endereco;

@Component
public class EnderecoMapper {

	@Autowired
	private ModelMapper modelMapper;

	public EnderecoModel toModel(Endereco endereco) {
		return modelMapper.map(endereco, EnderecoModel.class);
	}

	public List<EnderecoModel> toCollectionModel(List<Endereco> enderecos) {
		return enderecos.stream().map(endereco -> toModel(endereco)).collect(Collectors.toList());
	}

	public Endereco toDomain(EnderecoInput input) {
		return modelMapper.map(input, Endereco.class);
	}

	public void copyToDomainObject(EnderecoInput input, Endereco endereco) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// br.com.apssystem.algafood-api.domain.model.Endereco was altered from 1 to 2
		endereco.setCidade(new Cidade());
		modelMapper.map(input, endereco);
	}
}
