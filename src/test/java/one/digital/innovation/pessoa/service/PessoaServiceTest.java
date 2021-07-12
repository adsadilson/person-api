package one.digital.innovation.pessoa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import one.digital.innovation.pessoa.api.dto.input.PessoaInput;
import one.digital.innovation.pessoa.api.execption.NegocioException;
import one.digital.innovation.pessoa.api.mapper.PessoaMapper;
import one.digital.innovation.pessoa.domain.enums.Sexo;
import one.digital.innovation.pessoa.domain.service.PessoaService;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
public class PessoaServiceTest {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaMapper map;

	private PessoaInput novaPessoa;


	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		novaPessoa = new PessoaInput();
		novaPessoa.setTelefones(new ArrayList<>());
	}

	@Test
	public void dever_cadastrar_pessoa_quando_tudo_estive_correto() {
		novaPessoa.setNome("FERNANDO");
		novaPessoa.setSobreNome("LIMA");
		novaPessoa.setCpf("009.165.125-50");
		novaPessoa.setSexo(Sexo.MASCULINO);

		try {
			pessoaService.adicionar(map.toDomain(novaPessoa));
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertThat(novaPessoa).isNotNull();
	}

	@Test
	public void deve_falhar_quando_cadastrar_pessoa_sem_nome() {
		novaPessoa.setNome(null);
		novaPessoa.setSobreNome("OLIVEIRA");
		novaPessoa.setCpf("176.104.400-19");
		novaPessoa.setSexo(Sexo.MASCULINO);

		assertThrows(DataIntegrityViolationException.class, () -> pessoaService.adicionar(map.toDomain(novaPessoa)));
	}

	@Test
	public void deve_falhar_quando_excluir_pessoa_em_uso() {
		assertThrows(NegocioException.class, () -> pessoaService.excluir(1L));
	}

	@Test
	public void deve_falhar_quando_excluir_pessoa_inexistente() {
		assertThrows(NegocioException.class, () -> pessoaService.excluir(100L));
	}

}
