package one.digital.innovation.pessoa.api.execption;

public class EntidadeEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}

	public EntidadeEmUsoException(String classe, Long id) {
		super(String.format("%s de código %d não pode ser excluido pois está em uso", classe, id));
	}
}
