package platz.util;

import java.util.List;

import platz.dao.CategoriaDAO;
import platz.dao.EmpresaDAO;
import platz.dao.EventoDAO;
import platz.model.CategoriaEvento;
import platz.model.Empresa;
import platz.model.Evento;

public class Teste {
	
	public static void main(String args[]){
		
		
		CategoriaEvento categoria = new CategoriaDAO().buscarPorId(2);
		Empresa empresa = new EmpresaDAO().buscarPorId(1);
		
		List<Evento> eventosCategoria = new EventoDAO().listarPorCategoria(categoria);
		List<Evento> eventoDestaque = new EventoDAO().listarDestaques();
		List<Evento> eventoEmpresa = new EventoDAO().listarPorEmpresa(empresa);
		
		for(Evento evento: eventoEmpresa){
			
			System.out.println(evento.getEvento());
			System.out.println(evento.getId());
			System.out.println();

						
		}
		
	}
	
	
}
