package br.csi.barbeariabarbadus.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import br.csi.barbeariabarbadus.service.AutenticacaoService;



import java.io.IOException;

@Component
public class AutenticacaoFilter extends OncePerRequestFilter {

    private final TokenServiceJWT tokenServiceJWT;
    private final AutenticacaoService autenticacaoSerivce;
    public AutenticacaoFilter(TokenServiceJWT tokenServiceJWT, AutenticacaoService autenticacaoSerivce){
        this.tokenServiceJWT = tokenServiceJWT;
        this.autenticacaoSerivce = autenticacaoSerivce;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        System.out.println("Filtro para autenticacao e autorização");

        String tokenJWT = recuperarToken(request);
        System.out.println("TokenJWT:"+tokenJWT);
        // se nn existir token na requisicao o security config deve bloquear
        if (tokenJWT != null){
            String subject = this.tokenServiceJWT.getSubject(tokenJWT);
            System.out.println("Login da req. "+subject);

            UserDetails userDetails = this.autenticacaoSerivce.loadUserByUsername(subject);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token != null){
            return token.replace("Bearer", "").trim();
        }
        return null;
    }

}
