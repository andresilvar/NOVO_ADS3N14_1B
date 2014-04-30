


public class selectionSort {

	public static int comparacao = 0;
	public static int swap = 0;
	
	public static void main(String[] args) {
		
		
		int[] vetor = {0, 2, 100, 15, 45, 66, 80, 1, 1, 55, 14, 78, 6, 99, 88,15};
		
		
			
		
		System.out.println("Números desordenados");
		printVetor(vetor);
		
		
		
		System.out.println("---------");
		
		
		sortVetor(vetor);
		
		System.out.println("Números ordenados pelo método SelectionSort");
		printVetor(vetor);
		
		System.out.println("Foram efetuadas " + comparacao + " comparações.");
		System.out.println("E " + swap + " trocas.\n");
		
		
		
 
	}

	private static void sortVetor(int[] vetor) {
		int min;
	    for (int i = 0; i < vetor.length; i++) {	    	
	      
	        min = i;
	        for (int j = i + 1; j < vetor.length; j++) {
	      
	            if (vetor[j] < vetor[min]) {
	      
	                min = j;
	                swap++;
	            }
	            comparacao ++;
	        }
	       
	        if (min != i) {
	       
	            final int temp = vetor[i];
	       
	            vetor[i] = vetor[min];
	       
	            vetor[min] = temp;
	            
	        }	        
	    }
	}

	private static void printVetor(int[] vetor) {
		for(int i=0;i<vetor.length;i++){  
            System.out.println(vetor[i]);  
        }		 
	}

	
}
