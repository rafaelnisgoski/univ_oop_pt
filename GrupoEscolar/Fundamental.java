
public class Fundamental extends Estudante {
	private static final long serialVersionUID = 1L;
	private String contraturno;
	
	
	public Fundamental(String matricula, String nome, String responsavel, String contraturno) {
		super(matricula, nome, responsavel);
		this.ensino = "Ensino Fundamental";
		this.contraturno = contraturno;
	}
	
	public String detalhar() {
		String detalhe = "";
		detalhe += "Ensino: " + this.ensino + "\n";
		detalhe += "Atividades de Contraturno: " + this.contraturno + "\n";
		return detalhe;
	}
}
