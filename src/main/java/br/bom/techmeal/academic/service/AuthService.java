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
            //cria mecanismo de credencial
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
            //prepara mecanismo de autenticar
            Authentication authentication = authenticationManager.authenticate(userAuth);
            //busca usuario logado
            UserDetailImpl userAuthenticate = (UserDetailImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
            AcessDTO acessDto = new AcessDTO(token);

            return acessDto;

        }catch (BadCredentialsException e){
            // login ou senha invalido

        }
        return null;

    }

}
