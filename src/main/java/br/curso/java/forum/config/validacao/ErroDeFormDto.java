package br.curso.java.forum.config.validacao;

public class ErroDeFormDto {

    private String campo;
    private String erro;

    public ErroDeFormDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
