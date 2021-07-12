package one.digital.innovation.pessoa.api.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CidadeInput {

	@NotBlank
	@Size(min = 4, max = 100)
	private String nome;

	@Valid
	@NotNull
	private EstadoIdInput estado;
}
