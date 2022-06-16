package br.curso.java.forum.controller.dto.form;

import br.curso.java.forum.controller.repository.TopicoRepository;
import br.curso.java.forum.modelo.Topico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class AtualizacaoTopicoForm {

    @NotBlank
    @Length(min = 5)
    private String titulo;

    @NotBlank
    @Length(min = 5)
    private String mensagem;

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

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            topico.get().setTitulo(this.titulo);
            topico.get().setMensagem(this.mensagem);
            return topico.get();
        }
        return null;
    }
}
