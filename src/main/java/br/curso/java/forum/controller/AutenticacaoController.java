package br.curso.java.forum.controller;

import br.curso.java.forum.controller.dto.form.LoginForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @PostMapping
    public ResponseEntity autenticar(@RequestBody @Valid LoginForm form) {

        return new ResponseEntity("Email: [" + form.getEmail() + "] | senha: [" + form.getSenha() + "]", HttpStatus.OK);
    }

}
