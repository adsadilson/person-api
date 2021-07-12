package one.digital.innovation.pessoa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import one.digital.innovation.pessoa.domain.enums.TipoTelefone;

@Entity
@Table(name = "telefone")
@SequenceGenerator(name = "TELEFONE_ID", sequenceName = "TELEFONE_ID_SEQ")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TELEFONE_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private TipoTelefone tipo;

	@Column(length = 2, nullable = false)
	private String ddd;

	@Column(length = 14, nullable = false)
	private String numero;

	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Pessoa pessoa;

}
