package one.digital.innovation.pessoa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@SequenceGenerator(name = "ENDERECO_ID", sequenceName = "ENDERECO_ID_SEQ")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ENDERECO_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String logradouro;

	private String numere;

	private String bairro;

	private String complemento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

}
