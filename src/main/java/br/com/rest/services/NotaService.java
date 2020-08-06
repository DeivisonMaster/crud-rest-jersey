package br.com.rest.services;

import java.sql.SQLException;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.rest.dao.NotaDAO;
import br.com.rest.model.Nota;

@Path("/notas")
public class NotaService {
	
	private static final String CHARSET_UTF_8 = ";charset=utf-8";
	private NotaDAO dao;
	
	@PostConstruct
	public void init() {
		this.dao = new NotaDAO();
	}
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF_8)
	public Collection<Nota> getListarNotas(){
		
		Collection<Nota> notas = null;
		
		try {
			notas = this.dao.listarNotas();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notas;
	}
	
	@POST
	@Path("/adicionar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionaNota(Nota nota) {
		String msg = "";
		
		System.out.println(nota.getTitulo());
		
		try {
			
			this.dao.adicionaNota(nota);
			msg = "Nota adicionada com sucesso!";
			
		} catch (ClassNotFoundException | SQLException e) {
			msg = "Erro ao adicionar a nota!";
			e.printStackTrace();
		}
		return msg;
	}
	
	
	@GET
	@Path("/buscaPorId/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF_8)
	public Nota buscaPorId(@PathParam("id") Integer id) {
		Nota nota = null;
		
		try {
			
			nota = this.dao.buscarPorId(id);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return nota;
	}
	
	
	@PUT
	@Path("/editar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String editaNota(Nota nota, @PathParam("id") Integer id) {
		String msg = "";
		
		System.out.println(nota.getTitulo());
		
		try {
			this.dao.editarNota(nota, id);
			msg = "Nota editada com sucesso!";
			
		} catch (ClassNotFoundException | SQLException e) {
			msg = "Erro ao editar a nota";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	
	@DELETE
	@Path("/apagar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerNota(@PathParam("id") Integer id) {
		String msg = "";
		
		try {
			this.dao.removerNota(id);
			msg = "Nota removida com sucesso!";
			
		} catch (ClassNotFoundException | SQLException e) {
			msg = "Erro ao remover a nota";
			e.printStackTrace();
		}
		
		return msg;
	}
	
}










