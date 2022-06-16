package br.curso.java.forum.config.security;

import br.curso.java.forum.modelo.Usuario;
import br.curso.java.forum.controller.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> byEmail = usuarioRepository.findByEmail(username);
        if (byEmail.isPresent()) return byEmail.get();
        throw new UsernameNotFoundException("Dados incorretos");
    }
}
