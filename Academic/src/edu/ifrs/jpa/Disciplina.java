package edu.ifrs.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="0106119_disciplina")
public class Disciplina {
	@Id
	@GeneratedValue
	private long id;
	
	private String nome;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="disciplina")
	private List<Turma> turmas;
	
	public Disciplina(){
		this.turmas = new ArrayList<Turma>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public void addTurma(Turma turma){
		this.turmas.add(turma);
	}

}
