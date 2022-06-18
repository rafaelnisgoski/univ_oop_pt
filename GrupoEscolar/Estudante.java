
import java.io.Serializable;

public abstract class Estudante implements Serializable {
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String nome;
	private String responsavel;
	protected String ensino;
	
	public Estudante(String matricula, String nome, String responsavel) {
		this.matricula = matricula;
		this.nome = nome;
		this.responsavel = responsavel;
	}
	
	public String toString() {
		String retorno = "";
		retorno += "Matrícula:" + this.matricula + "\n";
		retorno += "Nome: " + this.nome + "\n";
		retorno += "Responsável: " + this.responsavel + "\n";
		retorno += detalhar();
		return retorno;
	}
	
	public abstract String detalhar(); 
}
