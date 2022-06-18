
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CadastroAlunos {
	// Atributos
	private ArrayList<Estudante> estudantes;
	
	// Construtor
	public CadastroAlunos() {
		this.estudantes = new ArrayList<Estudante>();
	}
	
	// Leitura
	public String[] lerValores(String[] dadosIn) {
		String[] dadosOut = new String[dadosIn.length];
		
		for(int i = 0; i < dadosIn.length; i++) {
			dadosOut[i] = JOptionPane.showInputDialog("Entre com " +dadosIn[i] + ": ");
		}
		return dadosOut;
	}
	
	// Inicialização de alunos
	public Pre lerPre() {
		String[] valores = new String[4];
		String[] nomeValores = {"Matrícula", "Nome", "Responsável", "Cuidados"};
		valores = lerValores(nomeValores);
		
		Pre aluno = new Pre(valores[0], valores[1], valores[2], valores[3]);
		return aluno;	
	}
	public Fundamental lerFundamental() {
		String[] valores = new String[4];
		String[] nomeValores = {"Matrícula", "Nome", "Responsável", "Atividades de Contraturno"};
		valores = lerValores(nomeValores);
		
		Fundamental aluno = new Fundamental(valores[0], valores[1], valores[2], valores[3]);
		return aluno;
	}
	public Medio lerMedio() {
		String[] valores = new String[4];
		String[] nomeValores = {"Matrícula", "Nome", "Responsável", "Oficinas Profissionalizantes"};
		valores = lerValores(nomeValores);
		
		Medio aluno = new Medio(valores[0], valores[1], valores[2], valores[3]);
		return aluno;
	}
	
	
	// Persistência de dados - gravação
	public void salvarCadastro(ArrayList<Estudante> estudantes) {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("./cadastroAlunos.dados"));
			for(int i = 0; i < estudantes.size(); i++) {
				outputStream.writeObject(estudantes.get(i));
			}
		} catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Impossível criar arquivo!");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// Persistência de dados - leitura
	@SuppressWarnings("finally")
	public ArrayList<Estudante> carregarCadastro() {
		ArrayList<Estudante> estudantesTemp = new ArrayList<Estudante>();
		
		ObjectInputStream inputStream = null;
		
		try {
			inputStream = new ObjectInputStream(new FileInputStream("./cadastroAlunos.dados"));
			Object obj = null;
			while((obj = inputStream.readObject()) != null) {
				if(obj instanceof Estudante) {
					estudantesTemp.add((Estudante) obj);
				}
			}
		} catch(EOFException e) {
			System.out.println("Fim de arquivo.");
		} catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Cadastro de alunos não existe!");
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(inputStream != null) {
					inputStream.close();
				}
			} catch(final IOException e) {
				e.printStackTrace();
			}
			return estudantesTemp;
		}
	}
	
	// Validações de Input
	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	public int retornaInteiro(String entrada) {	
		while(!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}
	
	// Menu
	public void menuCadastro() {
		String menu = "";
		String entrada;
		int opc1, opc2;
		do {
			menu = 	"Cadastro de Alunos\n"+
					"Opções:\n"+
					"1. Adicionar Aluno\n" +
					"2. Mostrar Alunos\n" +
					"3. Limpar Cadastro\n" +
					"4. Salvar Cadastro\n" +
					"5. Carregar Cadastro\n"+
					"9. Sair";
			entrada = JOptionPane.showInputDialog(menu + "\n\n");
			opc1 = retornaInteiro(entrada);
			
			switch(opc1) {
				// Adicionar Aluno
				case 1:
					menu =	"Entrada de Aluno\n" +
							"Opções:\n" +
							"1. Pré-Escolar\n" +
							"2. Ensino Fundamental\n" +
							"3. Ensino Médio";
					entrada = JOptionPane.showInputDialog(menu + "\n\n");
					opc2 = this.retornaInteiro(entrada);
								
					switch(opc2) {
						case 1:
							estudantes.add((Pre) lerPre());
							break;
						case 2:
							estudantes.add((Fundamental) lerFundamental());
							break;
						case 3:
							estudantes.add((Medio) lerMedio());
							break;
						default:
							JOptionPane.showMessageDialog(null, "Entrada não válida!");
					}
					break;
					
				// Mostrar Alunos
				case 2:
					if(estudantes.size() == 0) {
						JOptionPane.showMessageDialog(null, "Cadastro vazio! Adicione alunos...");
						break;
					}
					String dados = "";
					for(int i = 0; i < estudantes.size(); i++) {
						dados += estudantes.get(i).toString() + "-----------------------------------------\n";
					}
					JOptionPane.showMessageDialog(null, dados);
					break;
					
				// Limpar Cadastro
				case 3:
					if(estudantes.size() == 0) {
						JOptionPane.showMessageDialog(null, "Cadastro vazio! Adicione alunos...");
						break;
					}
					estudantes.clear();
					JOptionPane.showMessageDialog(null, "Cadastro LIMPO com sucesso!");
					break;
					
				// Salvar Cadastro
				case 4:
					if(estudantes.size() == 0) {
						JOptionPane.showMessageDialog(null, "Cadastro vazio! Adicione alunos...");
						break;
					}
					salvarCadastro(estudantes);
					JOptionPane.showMessageDialog(null, "Cadastro SALVO com sucesso!");
					break;
					
				// Carregar Cadastro
				case 5:
					estudantes = carregarCadastro();
					if(estudantes.size() == 0) {
						JOptionPane.showMessageDialog(null, "Sem dados para apresentar.");
						break;
					}
					JOptionPane.showMessageDialog(null, "Cadastro CARREGADO com sucesso!");
					break;
					
				// Sair
				case 9:
					JOptionPane.showMessageDialog(null, "Fechando cadastro");
					break;
			}
		} while (opc1 != 9);
	}
	
	
	// Execução
	public static void main(String[] args) {
		CadastroAlunos cadastro = new CadastroAlunos();
		cadastro.menuCadastro();
	}
}
