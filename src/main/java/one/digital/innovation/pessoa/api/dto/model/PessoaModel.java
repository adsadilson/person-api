package one.digital.innovation.pessoa.api.dto.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import one.digital.innovation.pessoa.domain.enums.Sexo;
import one.digital.innovation.pessoa.domain.enums.Status;

@Data
public class PessoaModel {

	private Long id;
	private String nome;
	private String sobreNome;
	private String cpf;
	private LocalDate nascimento;
	private LocalDate cadastro;
	private Sexo sexo;
	private Status status;
	
	private List<TelefoneModel> telefones;
}
