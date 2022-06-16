package br.curso.java.forum.controller.dto.form;

import br.curso.java.forum.modelo.Topico;
import br.curso.java.forum.controller.repository.CursoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class TopicoForm {

    @NotBlank @Length(min = 5)
    private String titulo;
    @NotBlank @Length(min = 10)
    private String mensagem;
    @NotBlank
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Topico topico = new Topico();

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        topico.setCurso(cursoRepository.findByNome(this.nomeCurso));
        return topico;
    }
}
