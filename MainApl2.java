//*************************** ATENÇÃO! ****************************
// O método main() deve ser alterado somente nos comentários TODO.
// Todas as outras instruções devem permanecer intactas e o código
// deve funcionar conforme descrito no enunciado da atividade.
//*************************** ATENÇÃO! ****************************
// arquivo: src/MainApl2.java

// TODO: Colocar a identificação dos(as) integrantes aqui.

//Jiye Huang RA:10438990
//Gabriel Mires Camargo RA:10436747
// Pedro Caetano de Toledo Piza RA:10426861
//Gustavo Kiyoshi Ikeda Ra:10439179

// TODO: Listar todas as referências consultadas para solucionar a atividade.

import apl2.DLinkedList;
import apl2.Data;
import apl2.LinkedListOriginal;
import apl2.Node;
import apl2.Operation;
import java.util.Scanner;

public class MainApl2 {
	
	public static void main(String[] args) {
		LinkedListOriginal list = new LinkedListOriginal();
		
		// TODO: Carregar o conteúdo do arquivo "dados.txt" e adicionar cada linha como um nó na LinkedListOriginal list.
		try{
			String[] data = Data.loadTextFileToString("dados.txt").split("\n");
			for(int i = 0; i < data.length; i++){
				String[] linha = data[i].split("#");
				list.append(Integer.parseInt(linha[0]), linha[1], Integer.parseInt(linha[2]), Integer.parseInt(linha[3]));
			}
			
		}catch(Exception e){
			System.out.println(e);
		}

		boolean menu = true;
		Scanner scan = new Scanner(System.in);

		System.out.println("Sistema Conversor de Notas");
		while(menu)
		{	
			
			System.out.println("1) Dados originais: lê arquivo dados.txt e apresenta todos os dados do Sistema de Notas Legado");
			System.out.println("2) Dados convertidos: gera arquivo dados.csv e apresenta todos os dados do Sistema de Notas Atualizado");
			System.out.println("3) Lista notas filtradas válidas: apresenta os dados somente das notas válidas filtradas");
			System.out.println("4) Lista notas filtradas inválidas: apresenta os dados somente das notas filtradas pela “ausência de notas”");
			System.out.println("5) Média de notas válidas: apresenta a média das notas válidas filtradas");
			System.out.println("6) Notas acima da média: apresenta os dados para as notas acima da média");
			System.out.println("7) Lista mapeada para uma única string: apresenta a String contendo os dados mapeados");
			System.out.println("8) Finaliza sistema");
			System.out.print("Digite uma opção ");
			int opcao = scan.nextInt();

			switch(opcao)
			{
				case 1: //Imprimi o conteúdo original da lista, armazenada na LinkedListOriginal
					System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>"); 
					System.out.println(list);
					System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
					break;
				case 2://Mapeia a LinkedListOriginal, armazenando o conteúdo na DLinkedList implementada e depois à transforma em uma String para assim salvar em um arquivo csv
					DLinkedList fixedList = Operation.map(list); 
					String contents = Operation.mapToString(fixedList);
					try{
						Data.saveStringToTextFile("dados.csv", contents);
						System.out.println("Arquivo csv gerado com sucesso");
					}catch(Exception e){
						System.out.println(e);
					}
					System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");//Imprimi o conteudo armazenado na DLinkedList
					System.out.println(fixedList);
					System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
					break;

				case 3: //Recebe a DLinkedList e filtra todas as notas inválidas(99.9) na lista, assim deixando apenas os alunos com notas válidas
					DLinkedList fixedList3 = Operation.map(list);
					DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList3);
					System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
					System.out.println(filteredGradedList);
					System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
					break;
				case 4: //Recebe a DlinkedList e filtra os alunos com notas válidas (diferentes de 99.9) e deixa apenas os alunos com ausência de nota na lista
					DLinkedList fixedList4 = Operation.map(list);
					DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList4);
					System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
					System.out.println(filteredNonGradedList);
					System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");
					break;
				case 5: //Recebe a DLinkedList e a filtra para deixar apenas os alunos com notas válidas em seu conteúdo, em seguida calcula a média das notas, a imprimindo
					DLinkedList fixedList5 = Operation.map(list);
					DLinkedList filteredGradedList5 = Operation.filterRemoveNonGraded(fixedList5);
					float average = Operation.reduce(filteredGradedList5);
					System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
					System.out.println(average);
					System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
					break;
				case 6://Recebe a DLinkedList e a filtra para deixar apenas os alunos com notas válidas em seu conteúdo, em seguida calcula a média das notas e filtra todos os alunos que possuem notas abaixo da média  
					DLinkedList fixedList6 = Operation.map(list);
					DLinkedList filteredGradedList6 = Operation.filterRemoveNonGraded(fixedList6);
					float average6 = Operation.reduce(filteredGradedList6);
					DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList6, average6);
					System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
					System.out.println(aboveAverageList);
					System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
					break;
				case 7: //Mapeia a LinkedListOriginal, armazenando o conteúdo na DLinkedList implementada e depois à transforma em uma String
					DLinkedList fixedList7 = Operation.map(list); 
					String contents7 = Operation.mapToString(fixedList7);
					System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
					System.out.println(contents7);
					System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
					break;
				case 8: //Encerra o programa
					menu = false;
					break;
				default: //Encerra o programa caso o usuário digite uma opção inválida
					System.out.println("Opção invalida, tente novamente mais tarde.");
					menu = false;
			}	
		}


		
		/*System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
		System.out.println(list);
		System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
		
		DLinkedList fixedList = Operation.map(list);
		System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
		System.out.println(fixedList);
		System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
		
		DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
		System.out.println(filteredGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
		
		DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
		System.out.println(filteredNonGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");

		float average = Operation.reduce(filteredGradedList);
		System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
		System.out.println(average);
		System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
		
		DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
		System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
		System.out.println(aboveAverageList);
		System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
		
		String contents = Operation.mapToString(fixedList);
		System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
		System.out.println(contents);
		System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
		
		
		// TODO: Salvar o conteúdo da String contents em um arquivo chamado "dados.csv".
		try{
			Data.saveStringToTextFile("dados.csv", contents);	
		}catch(Exception e){
			System.out.println(e);
		}
		Node test1 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");

		Node test2 = fixedList.removeNode("23.S1-999");
		System.out.println(">>>>>>>>>> test2 >>>>>>>>>>\n" + test2 + "\n<<<<<<<<<< test2 <<<<<<<<<<\n");

		Node test3 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test3 >>>>>>>>>>\n" + test3 + "\n<<<<<<<<<< test3 <<<<<<<<<<\n");

		aboveAverageList.clear();
		System.out.println(">>>>>>>>>> aboveAverageList.clear() >>>>>>>>>>\n" + aboveAverageList + "\n<<<<<<<<<< aboveAverageList.clear() <<<<<<<<<<\n");

		DLinkedList testList = new DLinkedList();
		testList.insert("ABC", "John Doe", 4.7f);
		testList.append("XYZ", "Jane Doe", 9.9f);
		testList.insert("321", "Test", 2.3f);
		testList.append("Nothing", "Yada yada yada", 99.9f);
		// TODO: Inserir um nó no início da lista testList com os dados ("ABC", "John Doe", 4.7f).
		// TODO: Inserir um nó no final da lista testList com os dados ("XYZ", "Jane Doe", 9.9f).
		// TODO: Inserir um nó no início da lista testList com os dados ("321", "Test", 2.3f).
		// TODO: Inserir um nó no final da lista testList com os dados ("Nothing", "Yada yada yada", 99.9f).
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail());
		System.out.println("testList.removeHead(): " + testList.removeHead());
		System.out.println("testList.removeTail(): " + testList.removeTail() + '\n');
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail());
		System.out.println("testList.removeNode(\"ABC\"): " + testList.removeNode("ABC") + '\n');
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail() + '\n');
		testList.insert("qwerty", "QWERTY", 1.2f);
		testList.append("WASD", "wasd", 3.4f);
		testList.insert("ijkl", "IJKL", 5.6f);
		testList.append("1234", "Um Dois Tres Quatro", 7.8f);
		// TODO: Inserir um nó no início da lista testList com os dados ("qwerty", "QWERTY", 1.2f).
		// TODO: Inserir um nó no final da lista testList com os dados ("WASD", "wasd", 3.4f).
		// TODO: Inserir um nó no início da lista testList com os dados ("ijkl", "IJKL", 5.6f).
		// TODO: Inserir um nó no final da lista testList com os dados ("1234", "Um Dois Tres Quatro", 7.8f).
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		testList.clear();
		System.out.println(">>>>>>>>>> testList.clear() >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList.clear() <<<<<<<<<<\n");*/
	}

}
