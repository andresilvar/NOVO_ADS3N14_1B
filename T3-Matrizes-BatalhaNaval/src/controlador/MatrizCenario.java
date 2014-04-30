package controlador;

import static java.lang.System.out;
import java.util.Scanner;
import modelo.NavioColocado;

public class MatrizCenario {


	private Scanner scanner;


	public void mostraTabela(String[][] table) {
		
		
		out.print(" ");
		for (char i = 'A'; i <= 'J'; i++) {
			out.print(" " + i);
		}

		out.println("");

		for (int i = 0; i <= 9; i++) {
			for (int z = 0; z <= 9; z++) {
		
				if (z == 0) {
					out.print(i + " ");
				}

		
				if (table[i][z] == "0" || table[i][z] == "1") {
					out.print(". ");
				} else if (table[i][z] == "2") {
					out.print("- ");
				} else if (table[i][z] == "3") {
					out.print("O ");
				}

			}
			out.println("");
		}
	}

	public String msgOpcoes() {
		out.println("Informe a Opção como no exemplo [c4]: ");
		scanner = new Scanner(System.in);
		return scanner.next();
	}

	
	public void msgGanhou() {
		out.println("Você Ganhou!!!");
	}
	
	
	public void msgPerdeu() {
		out.println("Você Perdeu!!!");
	}
	
	
	public void msgAcertouAgua() {
		out.println("Você acertou na Água!");
	}

 
	
	public void msgPontos(int count)
	{
		out.println("Restam " + count + " Pontos.");
	}

	
	public void msgAcertou(NavioColocado ship) {
		out.println("Você acertou um " + ship.getNome());
	}
	
	
	public void msgDestruiu(NavioColocado ship)
	{
		out.println("Você destruiu um " + ship.getNome());
	}
	
	
		public void msgErro(String mensagem) {
			
			out.println("--------------------");
			out.println(mensagem);
			out.println("--------------------");
		}

}
