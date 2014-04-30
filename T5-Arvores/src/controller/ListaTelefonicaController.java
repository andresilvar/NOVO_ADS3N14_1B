package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Contato;

import view.Screen;




public class ListaTelefonicaController {
	
	private Screen objScreen = new Screen();
	private Contato objContato = new Contato();
	private String contactFile = "contatos.txt"; 
	
	public ListaTelefonicaController() {
	}

	public void start(){
		
		loadFile(contactFile);
		
		char opt = ' ';
		String input;
		
		objScreen.showHead();
		
		while (opt != 's'){
		
			opt = Character.toLowerCase(objScreen.showSingleInputScreen("Pressione:\n [I] para INSERIR\n [E] para EXCLUIR\n [P] para PROCURAR\n [L] para LISTAR\n [S] SAIR\n",true));
		
			switch (opt){
				case 'i':
					
					String nome =  objScreen.showInputScreen("Insira o nome",true);
					String tel =  objScreen.showInputScreen("Insira o telefone",true);
					objContato.cadastrar(nome, tel);
				
					
					
				break;
				
				case 'p':
					input =  objScreen.showInputScreen("Insira o nome a ser pesquisado",true);
					
					if (objContato.procurar(input)){
						objScreen.showContact(objContato.getNome(),objContato.getTelefone(),objContato.getQtdComparacoes());
					}
					else {
						objScreen.showWarnMsg("CONTATO NÃO ENCONTRADO",3000);
					}
				break;
				
				case 'l':
					char sOpt = ' ';
					boolean sair = false;
					
					while (!sair){
					
						sOpt = Character.toLowerCase(objScreen.showSingleInputScreen("Pressione:\n A - Travessia prefixa \n B - Travessia infixa\n C - Travessia posfixa\n D - Busca em largura\n E - Busca em profundidade", true));
						
						switch (sOpt){
						
							case 'a':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Travessia prefixa");
								sair = true;
							break;
						
							case 'b':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Travessia infixa");
								sair = true;								
							break;
						
							case 'c':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Travessia posfixa");
								sair = true;								
							break;
						
							case 'd':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Busca em largura");
								sair = true;								
							break;
						
							case 'e':
								objScreen.showContactList(objContato.listarContatos(sOpt),"Busca em profundidade");
								sair = true;								
							break;
							
							default:
								objScreen.showWarnMsg("Opção inválida de listagem", 3000);
							break;
						}
					}
				break;
				
				case 'e':
					input =  objScreen.showInputScreen("Insira o nome a ser excluído",true);
					
					if(objContato.excluir(input) == 1){
						objScreen.showWarnMsg("Contato Excluído com sucesso",2000);
					}
					else {
						objScreen.showWarnMsg("Contato não encontrado",2000);
					}
				break;
				
				case 's':
					saveFile(contactFile);
					objScreen.showMessage("Aplicativo encerrado !");
				break;
			}
		}
	}
	
	
	private void loadFile(String filename) {
		
		try {
			
			@SuppressWarnings("resource")
			Scanner arq = new Scanner(new FileReader(filename));
			
			while(arq.hasNext()) {				
				objContato.cadastrar(arq.nextLine(), arq.nextLine());
			}
		} 
		catch (FileNotFoundException e) {
			objScreen.showMessage(e.getMessage());
		}
	}
	
	public void saveFile(String filename) {
		
		FileWriter arq = null;
		try {
			arq = new FileWriter(filename,false);
			
			ArrayList<String[]> lstFinal = new ArrayList<>();
			
			lstFinal = objContato.listarContatos('c');
			
			for (int i=0;i < lstFinal.size();i++){
				arq.append(lstFinal.get(i)[0] +"\n");
				arq.append(lstFinal.get(i)[1] +"\n");
			}
			
			
		} catch (IOException e) {
			objScreen.showMessage(e.getMessage());
		} finally {
			if (arq != null)
				try {
					arq.close();
				} catch (IOException e) {
					objScreen.showMessage(e.getMessage());
				}
		}
	}
}
