
public class Pre extends Estudante {
	private static final long serialVersionUID = 1L;
	private String cuidados;
	
	
	public Pre(String matricula, String nome, String responsavel, String cuidados) {
		super(matricula, nome, responsavel);
		this.ensino = "Pré-escolar";
		this.cuidados = cuidados;
	}
	
	public String detalhar() {
		String detalhe = "";
		detalhe += "Ensino: " + this.ensino + "\n";
		detalhe += "Cuidados: " + this.cuidados + "\n";
		return detalhe;
	}

}
