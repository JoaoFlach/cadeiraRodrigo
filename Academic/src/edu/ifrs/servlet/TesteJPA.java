package edu.ifrs.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ifrs.ejb.DAO;
import edu.ifrs.jpa.Disciplina;
import edu.ifrs.jpa.Turma;


@WebServlet("/go")
public class TesteJPA extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private DAO dao;
       
   
    public TesteJPA() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void execute(HttpServletRequest request, HttpServletResponse response) {
		Disciplina disciplina = new Disciplina();
		disciplina.setNome("Java");
		
		Turma turma = new Turma();
		turma.setCodigo("INF01");
		
		disciplina.addTurma(turma);
		turma.setDisciplina(disciplina);
		
		dao.create(disciplina);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
