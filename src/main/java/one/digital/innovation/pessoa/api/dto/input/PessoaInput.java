package one.digital.innovation.pessoa.api.dto.input;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digital.innovation.pessoa.domain.enums.Sexo;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaInput {

	@NotBlank
	@Size(min = 4, max = 100)
	private String nome;

	@NotBlank
	@Size(min = 4, max = 100)
	private String sobreNome;

	@CPF
	private String cpf;

	private LocalDate nascimento;
	
	@NotNull
	private Sexo sexo;

	@Valid
	private List<TelefoneInput> telefones;
}
