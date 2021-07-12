package one.digital.innovation.pessoa.api.dto.input;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EstadoIdInput {

	@NotNull
	private Long id;
}
