package apl2;
// arquivo: src/apl2/Node.java

// TODO: Colocar a identificação dos(as) integrantes aqui.



// -- A classe Node (que pertence ao pacote apl2) deve conter os atributos que
// representam a nova versão dos dados de uma pessoa, conforme descrito no
// enunciado da atividade Apl2.
// -- A classe deve conter os construtores apropriados, assim como os métodos
// getters e setters.
// -- A classe também representa um nó que é usado na implementação da lista
// duplamente encadeada (classe DLinkedList).
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com os valores dos atributos da classe.

public class Node {
	
	private String id;
	private String nome;
	private float nota;
	public Node ant;
	public Node prox;

	public Node(){
		this(null, "", (float) 99.9, null, null);
	}

	public Node(String id, String nome, float nota, Node ant, Node prox){
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.ant = ant;
		this.prox = prox;
	}
	
	public String getId(){
		return id;
	}

	public void setid(String id){
		this.id = id;
	}

	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public float getNota(){
		return nota;
	}

	public void setNota(float nota){
		this.nota = nota;
	}

	public Node getProx(){
		return prox;
	}

	public void setProx(Node prox){
		this.prox = prox;
	}

	public Node getAnt(){
		return ant;
	}

	public void setAnt(Node ant){
		this.ant = ant;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(getAnt() == null){
			sb.append(""+null);
		}
		else{
			sb.append(""+getAnt().id);
		}
		
		sb.append(" <- (")
		.append(getId())
		.append("; ")
		.append(getNome())
		.append("; ")
		.append(getNota())
		.append(") -> ");

		if(getProx() == null){
			sb.append(""+null);
		}
		else{
			sb.append(""+getProx().id);
		}			



		//sb.append("\n");
			
		return sb.toString();
	}
}