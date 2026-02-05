package alura.com.ForumHub.infra.security;


import alura.com.ForumHub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 👇 IGNORA O LOGIN
        //System.out.println("chegou até aqui");
        if (request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            //System.out.println("chegou até aqui");
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = usuarioRepository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario, null, usuario.getAuthorities()
            );
            System.out.println("aqui");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
