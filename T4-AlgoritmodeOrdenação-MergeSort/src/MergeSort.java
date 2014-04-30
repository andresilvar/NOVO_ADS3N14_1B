


public class MergeSort {

	public static int comparacao = 0;
	public static int swap = 0;
	
	
	public static void main(String[] args) {
		
		
		
		
		int[] vetor = {0, 2, 100, 15, 45, 66, 80, 1, 1, 55, 14, 78, 6, 99, 88,15};
		
		
			
		
		System.out.println("Números desordenados");
		printVetor(vetor);
		
		
		System.out.println("---------");
		
		
		sortVetor(vetor);
		
		System.out.println("Números ordenados pelo método MergeSort");
		printVetor(vetor);
		
		System.out.println("Foram efetuadas " + comparacao + " comparações.");

		System.out.println("E " + swap + " trocas.\n");
		
		    
		
		
	}
	
	private static int[] sortVetor(int[] vetor) {
		if (vetor.length == 1) {
		    return vetor;
		}
		else {
		
		    int[] esquerda = new int[vetor.length/2];
		    System.arraycopy(vetor, 0, esquerda, 0, esquerda.length);

		
		    int[] direita = new int[vetor.length-esquerda.length];
		    System.arraycopy(vetor, esquerda.length, direita, 0, direita.length);
		    
		
		    esquerda = sortVetor(esquerda);
		    direita = sortVetor(direita);

		
		    merge(esquerda, direita, vetor);

		    return vetor;
		    
		}
		
	
		
	}

	private static void merge(int[] esquerda, int[] direita, int[] vetor) {
		int indiceEsquerdo = 0;
		int indiceDireito = 0;
		int vetorIndice = 0;
		
		while (indiceEsquerdo < esquerda.length &&
				indiceDireito < direita.length) {
		    if (esquerda[indiceEsquerdo] < direita[indiceDireito]) {
		    	vetor[vetorIndice] = esquerda[indiceEsquerdo];
		    	indiceEsquerdo++;
		    	swap++;
		    }
		    else {
		    	vetor[vetorIndice] = direita[indiceDireito];
		    	indiceDireito++;
		    }		    
		    vetorIndice++;
		}
		
		int[] resto;
		int indiceResto;
		if (indiceEsquerdo >= esquerda.length) {		    
			resto = direita;
			indiceResto = indiceDireito;
		}
		else {
			resto = esquerda;
			indiceResto = indiceEsquerdo;
		}

		for (int i=indiceResto; i<resto.length; i++) {
			vetor[vetorIndice] = resto[i];
			vetorIndice++;
			comparacao++;
		}
		
	}

	private static void printVetor(int[] vetor) {
		for(int i=0;i<vetor.length;i++){  
            System.out.println(vetor[i]);  
        }		 
	}

	
		 	
}
	
