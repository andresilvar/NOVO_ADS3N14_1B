package view;

import java.util.ArrayList;
import java.util.Scanner;

public class Screen {

	Boolean debug = true;
	
	public void showHead(){
		System.out.println("-----------------------------------------------------------");
		System.out.println("Lista telefônica-Arvore Binária");
		System.out.println("-----------------------------------------------------------");
	}
	
	public void newScreen (){
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
		System.out.println("                                                      ");
	}
	
	public void showMessage(String msg){
		
		System.out.println(msg);
	}
	
	public void showDebugMessage(String msg){
		if (this.debug){
			System.out.println("DEBUG: " + msg);
		}
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void showContact(String nome, String telefone, int comp){
		
		System.out.println("------------------- CONTATO ENCONTRADO ---------------------");
		System.out.println("NOME: " + nome);
		System.out.println("TELEFONE: " + telefone);
		System.out.println("QUANTIDADE DE COMPARAÇÕES: " + comp);
		System.out.println("------------------------------------------------------------");
	}
	
	public void showContactList(ArrayList<String[]> in,String order){
		System.out.println("------------------- LISTAGEM DE CONTATOS ORDENACAO "+ order +" ---------------------");
		for (int i=0;i<in.size();i++){
			System.out.print(" CONTATO NRº: " + (i + 1) + "\n");
			System.out.print(" NOME: "+ in.get(i)[0] + "\n");
			System.out.print(" TELEFONE: "+ in.get(i)[1] + "\n");
			System.out.println("--------------------------------------------------");
			
		}
		System.out.print("\n\n\n");
	}
	
	public void showWarnMsg(String msg,int sleepTime){
		
		
		if (sleepTime < 1)
			return;
		
		System.out.println(msg);
		
		try {
			Thread.sleep(sleepTime);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public String showInputScreen(String msg,boolean vrfIn){
		
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String ret = "";
		
		if (vrfIn){
			while (ret.equals("")){
				System.out.print(msg + ":");
				ret = sc.nextLine();
			}
		}
		else {
			System.out.print(msg + ":");
			ret = sc.nextLine();
		}
		
		return ret;
	}
	
	public char showSingleInputScreen(String msg,boolean vrfIn){
		
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		char ret = ' ';
		
		if (vrfIn){
			
			do {
				System.out.print(msg + ":");
				ret = sc.next().toCharArray()[0];
				
			} while (!Character.isLetter(ret));
		}
		else {
			System.out.print(msg + ":");
			ret = sc.next().charAt(0);
		}
		
		return ret;
	}

}
