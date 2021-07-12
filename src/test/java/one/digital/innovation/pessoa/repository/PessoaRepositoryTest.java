package one.digital.innovation.pessoa.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import one.digital.innovation.pessoa.api.execption.NegocioException;
import one.digital.innovation.pessoa.domain.entity.Pessoa;
import one.digital.innovation.pessoa.domain.service.PessoaService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PessoaRepositoryTest {

	@Autowired
	private PessoaService pessoaRepository;

	@LocalServerPort
	private int port;

	@Test
	public void deve_procurar_pessoa_pelo_cpf() throws Exception {
		Pessoa pessoa = pessoaRepository.findByCpf("43349353037");
		assertThat(pessoa != null).isTrue();
		assertThat(pessoa.getNome()).isEqualTo("Fernanda");
		assertThat(pessoa.getCpf()).isEqualTo("43349353037");
	}

	@Test
	public void nao_deve_encontrar_pessoa_de_cpf_inexistente() {
		assertThrows(NegocioException.class, () -> pessoaRepository.findByCpf("727740410"));
	}

	@Test
	public void deve_encontrar_pessoa_pelo_ddd_e_numero_de_telefone() throws Exception {
		Pessoa pessoa = pessoaRepository.findByTelefoneDddAndTelefoneNumero("41", "89945933");
		assertThat(pessoa != null).isTrue();
		assertThat(pessoa.getNome()).isEqualTo("Fernanda");
		assertThat(pessoa.getCpf()).isEqualTo("43349353037");
	}

	@Test
	public void nao_deve_encontrar_pessoa_cujo_ddd_e_telefone_nao_estejam_cadastrados() {
		assertThrows(NegocioException.class,
				() -> pessoaRepository.findByTelefoneDddAndTelefoneNumero("11", "324516731"));
	}
}
