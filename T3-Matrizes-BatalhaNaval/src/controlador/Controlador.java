package controlador;

import java.util.ArrayList;
import java.util.Random;

import modelo.*;


public class Controlador {
	
	ArrayList<Navio> listaNavio;
	ArrayList<NavioColocado> listaNaviosColocados;
	
	String[][] tabela = new String[10][10];
	
	MatrizCenario matriz;
	int chances;

    
	
	public Controlador() {
		
		listaNavio = new ArrayList<Navio>();
		listaNaviosColocados = new ArrayList<NavioColocado>();
	
		chances = 15;

		
		listaNavio.add(new Navio("Porta-aviões", 5));
		listaNavio.add(new Navio("Destroyer", 4));
		listaNavio.add(new Navio("Destroyer", 4));
		listaNavio.add(new Navio("Fragata", 3));
		listaNavio.add(new Navio("Fragata", 3));
		listaNavio.add(new Navio("Torpedeiro", 2));
		listaNavio.add(new Navio("Torpedeiro", 2));
		listaNavio.add(new Navio("Torpedeiro", 2));
		listaNavio.add(new Navio("Submarino", 1));
		listaNavio.add(new Navio("Submarino", 1));
		listaNavio.add(new Navio("Submarino", 1));
		listaNavio.add(new Navio("Submarino", 1));
		listaNavio.add(new Navio("Submarino", 1));

		matriz = new MatrizCenario();

		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				tabela[i][j] = "0";
			}
		}

		montaTabela();
	}




	public void mostraTabela() {
		
		matriz.mostraTabela(this.tabela);
		
		String coordenada = matriz.msgOpcoes();
		if (coordenada.length() == 2)
		{
			tiro(coordenada);
		} else {
			matriz.msgErro("Digite uma opção correta!");
			mostraTabela();
		}
		
	}
	


	private void tiro(String coord)
	{
		int x = Integer.parseInt(coord.substring(1, 2));
		int y = Utils.converteLetraPraInteiro(coord.charAt(0));
		
		String value = tabela[x][y];
		
		if (value.equals("0"))
		{
			tabela[x][y] = "2";
			this.chances--;
			matriz.msgAcertouAgua();
			matriz.msgPontos(this.chances);
			
		} else if(value.equals("1"))
		{
			tabela[x][y] = "3";
			this.chances--;
			this.chances += 3;
			hitShip(x, y);
			matriz.msgPontos(this.chances);
		}
		else
		{
			matriz.msgErro("Opção já informada!");
		}
		
		verificaStatus();
		mostraTabela();
	}


	private void montaTabela() {
		boolean ok = false;
		
		for (Navio item : listaNavio) {
			ok = false;
			while (!ok) {

				boolean horizontal = (new Random()).nextBoolean();
				int iniPosDin = (new Random()).nextInt(10 - item.getTamanho());
				int iniPosFix = (new Random()).nextInt(10);


				ok = checkShip(iniPosFix, iniPosDin, horizontal,
						item.getTamanho());


				if (ok) {
					colocaNavio(iniPosFix, iniPosDin, horizontal,
							item.getTamanho(), item.getNome());
				}
			}
		}
	}


	private boolean checkShip(int iniPosFix, int iniPosDin, boolean horizontal, int tamanho) {
		
		boolean ok = true;
		

		if (horizontal) {
			if (iniPosDin + tamanho <= 9) {
				for (int i = iniPosDin; i < 10 - tamanho; i++) {
					if (tabela[i][iniPosFix] == "1") {
						ok = false;
						break;
					}
				}
			} else {
				ok = false;
			}
		}
		// Vertical
		else {
			if (iniPosDin + tamanho <= 9) {
				for (int i = iniPosDin; i < 10 - tamanho; i++) {
					if (tabela[iniPosFix][i] == "1") {
						ok = false;
						break;
					}
				}
			}
			else
			{
				ok = false;
			}
		}

		return ok;
	}
	

		private void hitShip(int x, int y)
		{

			for (NavioColocado i : listaNaviosColocados)
			{

				for (String c : i.getcolocaCoordenada())
				{
 
					if (c.equals(x + "" + y)){
						i.setContador(i.getContador()+1);
						matriz.msgAcertou(i);
						if (i.getContador() == i.getTamanho())
						{
							this.chances += 5;
							matriz.msgDestruiu(i);
						}
						break;
					}
				}
				
			}
		}


	private void colocaNavio(int iniPosFix, int iniPosDin, boolean horizontal, int tamanho, String nome) {
		NavioColocado deployed = new NavioColocado(nome, tamanho);
		

		if (horizontal) {
			for (int i = iniPosDin; i < iniPosDin + tamanho; i++) {
				tabela[i][iniPosFix] = "1";
				deployed.addcolocaCoordenada(i + "" + iniPosFix);
			}
		}

		else {
			for (int i = iniPosDin; i < iniPosDin + tamanho; i++) {
				tabela[iniPosFix][i] = "1";
				deployed.addcolocaCoordenada(iniPosFix + "" + i);
			}
		}
		
		listaNaviosColocados.add(deployed);
	}

	

	private void verificaStatus()
	{
		verificaGanhou();
		verificaDerrota();
	}
	

	private void verificaGanhou()
	{
		boolean Ok = true;
		for(NavioColocado n : listaNaviosColocados)
		{
			if (n.getTamanho() != n.getContador())
			{
				Ok = false;
				break;
			}
		}
		
		if (Ok)
		{
			matriz.msgGanhou();
			System.exit(0);
		}
	}
	

	private void verificaDerrota()
	{
		if(chances == 0)
		{
			matriz.msgPerdeu();
			System.exit(0);
		}
	}
	
}
