package br.com.curso.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.curso.forum.repository.CursoRepository;


public class TopicoForm {

	@NotNull @NotEmpty @Length(min = 5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min = 10)
	private String mensagem;
	
	@NotNull @NotEmpty
	private String nomeCurso;
	
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
}
