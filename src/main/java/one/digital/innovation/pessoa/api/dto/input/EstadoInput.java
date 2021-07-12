package one.digital.innovation.pessoa.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EstadoInput {

	@NotBlank
	@Size(min = 4)
	private String nome;

	@NotBlank
	@Size(min = 2, max = 2)
	private String sigla;

	@NotBlank
	private String capital;

	@NotBlank
	private String regiao;

}
