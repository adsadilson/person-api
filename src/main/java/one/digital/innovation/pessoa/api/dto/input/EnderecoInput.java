package one.digital.innovation.pessoa.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EnderecoInput {

	@NotBlank
	private String logradouro;

	@NotBlank
	private String numere;

	@NotBlank
	private String bairro;

	
	private String complemento;

	@Valid
	@NotNull
	private CidadeIdInput cidade;
	
	@Valid
	@NotNull
	private PessoaIdInput pessoa;

}
