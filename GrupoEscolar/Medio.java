
public class Medio extends Estudante {
	private static final long serialVersionUID = 1L;
	private String oficinas;
	
	
	public Medio(String matricula, String nome, String responsavel, String oficinas) {
		super(matricula, nome, responsavel);
		this.ensino = "Ensino Médio";
		this.oficinas = oficinas;
	}
	
	public String detalhar() {
		String detalhe = "";
		detalhe += "Ensino: " + this.ensino + "\n";
		detalhe += "Oficinas Profissionalizantes: " + this.oficinas + "\n";
		return detalhe;
	}
}
