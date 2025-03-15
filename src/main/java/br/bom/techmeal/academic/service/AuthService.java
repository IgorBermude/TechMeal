package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.dto.AcessDTO;
import br.bom.techmeal.academic.dto.AuthenticationDTO;
import br.bom.techmeal.academic.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    public AcessDTO login(AuthenticationDTO authDto){
        try {
            // Cria o token de autenticação
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getSenhaUsuario());

            // Autentica o usuário
            Authentication authentication = authenticationManager.authenticate(userAuth);

            // Se a autenticação for bem-sucedida, gera o token JWT
            UserDetailImpl userAuthenticate = (UserDetailImpl) authentication.getPrincipal();
            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
            AcessDTO acessDto = new AcessDTO(token);

            return acessDto;
        } catch (BadCredentialsException e) {

            return null;
        }
    }




}
