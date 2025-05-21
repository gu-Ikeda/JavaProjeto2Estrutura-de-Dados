package apl2;
// arquivo: src/apl2/DLinkedList.java

// TODO: Colocar a identificação dos(as) integrantes aqui.



// -- A classe DLinkedList (que pertence ao pacote apl2) deve implementar uma
// lista duplamente encadeada. Os nós dessa lista são do tipo [da classe] Node.
// -- A classe deve possuir dois nós especiais, head e tail, que são
// referências para o primeiro e último nó da lista, respectivamente.
// -- A classe deve possuir um contador de quantos nós existem na lista.
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com o conteúdo da lista.
// -- A classe deve implementar as operações a seguir, respeitando o
// comportamento descrito em cada operação.

public class DLinkedList {
	
	private Node head; 
	private Node tail;
 	private int count;


// OPERAÇÃO:		Método construtor
// COMPORTAMENTO:	Cria uma lista vazia.
	public DLinkedList() {
		this.head = null;
		this.tail = null;
		this.count = 0;
	}


// OPERAÇÃO:		insert(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no início da lista.
	public void insert(String id, String nome, float nota) {
		Node node = new Node(id, nome, nota, null, null);
		if(isEmpty())
		{
			tail = node; //tail aponta para o novo nó caso a lista estaja vaiza
		}
		else
		{
			head.ant = node; //nó existente aponta para o novo nó
			node.prox = head;
			
		}
		//node.prox = head; // novo nó aponta para o nó existente ou para se mesmo caso a lista esteja vazia
		head = node; //head aponta para o novo nó
		count++;
	}


// OPERAÇÃO:		append(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no final da lista.
	public void append(String id, String nome, float nota) {
		Node node = new Node(id, nome, nota, null, null);
		if(isEmpty())
		{
			head = node; //head aponta para o novo nó caso a lista esteja vazia
		}
		else
		{
			tail.prox = node; //nó existente aponta para o novo nó 
			node.ant = tail; //novo nó aponta para o nó existente ou para si mesmo caso a lista esteja vazia
		}
		tail = node; //tail aponta para o novo nó
		count++;
	}


// OPERAÇÃO: 		removeHead()
// COMPORTAMENTO:	Remove o nó do início da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeHead() {
		if(isEmpty())
		{
			return null; //retorna null caso não exista elementos
		}
		Node aux = head; //cria um ponteiro auxiliar que aponta para head
		if(head == tail)
		{
			tail = null; //caso exista apenas um elemento, tail apontará para null
		}
		else
		{
			head.prox.ant = null; //remove o ponteiro do nó seguinte que esta apontando para o nó atual a ser removido
		}
		head = head.prox; // head aponta para o nó seguinte ou head aponta para null caso exista apenas um elemento
		aux.prox = null; //remove o ponteiro do nó atual que esta apontando para o nó seguinte ou remove o nó atual caso ele seja o unico existente
		count--;
		return aux;
	}


// OPERAÇÃO:		removeTail()
// COMPORTAMENTO:	Remove o nó do final da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeTail() {
		if(isEmpty())
		{
			return null; //retorna null caso não exista elementos
		}
		Node aux = tail; //cria um ponteiro auxiliar que aponta para head
		if(head == tail)
		{
			head = null; // caso exista apenas um elemento, head apontará para null
		}
		else
		{
			tail.ant.prox = null; //remove o ponteiro do nó anterior que esta apontando para o nó atual a ser removido
		}
		tail = tail.ant; //tail aponta para o nó anterior ou tail aponta para null caso exista apenas um elemento
		aux.ant = null; //remove o ponteiro do nó atual que esta apontando para o nó anterior ou remove o nó atual caso seja o unico existente
		count--;
		return aux;
	}


// OPERAÇÃO:		removeNode(<ID da pessoa>)
// COMPORTAMENTO:	Remove o nó que contém o <ID da pessoa> da lista e retorna
//					a referência do nó removido.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node removeNode(String id) {
		if(isEmpty())
		{
			return null; //retorna null caso não exista elementos
		}
		Node aux = head; //cria um ponteiro que aponta para head

		while(aux != null) //percorre toda a lista
		{
			if(aux.getId().equals(id))
			{
				if(aux == head){
					head = aux.prox;
					head.ant = null;
					aux.ant = aux.prox = null;
					count--;
					return aux;
				}
				if(aux == tail){
					tail = aux.ant;
					tail.prox = null;
					aux.ant = aux.prox = null;
					count--;
					return aux;
				}
				aux.ant.prox = aux.prox; //o ponteiro do nó anterior que aponta para o nó atual passa a apontar para o nó seguinte
				aux.prox.ant = aux.ant; //o ponteiro do nó seguinte que aponta para o nó atual passa a apontar para o nó anterior
				aux.prox = null; //remove o ponteiro do nó atual que aponta para o nó seguinte 
				aux.ant = null; // remove o ponteiro do nó atual que aponta para o nó anteroir
				count--;
				return aux;
			}
			aux = aux.prox; // avança a lista
		}
		return null; //caso o elemento não exista
	}


// OPERAÇÃO:		getHead()
// COMPORTAMENTO:	Retorna uma referência para o nó do início da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getHead() {
		if(isEmpty())
		{
			return null;
		}
		return head; 
	}


// OPERAÇÃO:		getTail()
// COMPORTAMENTO:	Retorna uma referência para o nó do final da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getTail() {
		if(isEmpty())
		{
			return null;
		}
		return tail;
	}


// OPERAÇÃO:		getNode(<ID da pessoa>)
// COMPORTAMENTO:	Retorna uma referência para o nó que contém o <ID da pessoa>
//					da lista.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node getNode(String id) {
		if(isEmpty())
		{
			return null; //retorna null caso não exista elementos
		}
		Node aux = head; //cria um ponteiro que aponta para head
		while(aux != null) //percorre a lista
		{
			if(aux.getId().equals(id))
			{
				return aux; //retorna o id
			}
			aux = aux.prox; //percorre a lista
		}
		return null; //retorna null caso o elemento não exista
	}


// OPERAÇÃO:		count()
// COMPORTAMENTO:	Retorna a quantidade de nós da lista.
	public int count() {
		return count;
	}


// OPERAÇÃO:		isEmpty()
// COMPORTAMENTO:	Retorna true se a lista estiver vazia ou false, caso contrário.
	public boolean isEmpty() {
		return count == 0;
	}


// OPERAÇÃO:		clear()
// COMPORTAMENTO:	Esvazia a lista, liberando a memória de todos os nós da lista.
	public void clear() {
		Node aux = head; //cria um ponteiro auxiliar que aponta para head
		while(aux != null)
		{
			Node proximo = aux.prox; //cria um nó que armazena o próximo nó 
			aux.ant = null; //remove o ponteiro do nó atual que esta apontando para o nó anterior
			aux.prox = null; //remove o ponteiro do nó atual que esta apontando para o nó seguinte
			aux = proximo; //o ponteiro auxiliar aponta para o elemento seguinte
		}
		head = null;
		tail = null;
		count = 0;
	}


// OPERAÇÃO:		toString()
// COMPORTAMENTO:	Retorna uma string com o conteúdo da lista (caso queira, use o
//					exemplo do método toString() da classe LinkedListOriginal).
	@Override
	public String toString() {
		/*String s = "";
		
		for(Node no = head; no != null; no = no.getProx()){
			System.out.println("Pedro");
			s += "("+no.getId()+" ; "+no.getNome()+" ; "+no.getNota()+") -> \n"; 
		}
		
		return s;*/
		StringBuilder sb = new StringBuilder();
		
		sb.append("(" + count + ") \n");
		
		Node node = head;
		while (node != null) {

			/*sb.append(""+node.getAnt())
			.append(" <- (")
			.append(node.getId())
			.append(" ; ")
			.append(node.getNome())
			.append(" ; ")
			.append(node.getNota())
			.append(") -> ")
			.append(""+node.getProx()+"\n");*/
			sb.append(""+node+"\n");
			node = node.getProx();
		}
		//sb.append(".\n");
		
		return sb.toString();
	}

}