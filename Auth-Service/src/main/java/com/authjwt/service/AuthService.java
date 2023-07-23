package com.authjwt.service;

import com.authjwt.dto.AddUserDto;
import com.authjwt.dto.NewUserDto;
import com.authjwt.dto.RequestDto;
import com.authjwt.dto.TokenDto;
import com.authjwt.entity.AuthUser;
import com.authjwt.repository.AuthRepository;
import com.authjwt.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JWTProvider jwtProvider;


    //Cambiamos AddUserDto por NewUSerDto ya que la nueva entidad contiene el atributo de rol
    public AuthUser saveUser(NewUserDto addUserDto){
       Optional<AuthUser> user =  authRepository.findByUserName(addUserDto.getUserName());
       if (user.isPresent()){
           return null; // Ya esta presente el usuario no es necesario crearlo
       }
       String password = passwordEncoder.encode(addUserDto.getPassword());
       AuthUser saveUser = AuthUser.builder()
               .userName(addUserDto.getUserName())
               .password(password)
               .role(addUserDto.getRole())
               .build();

       return authRepository.save(saveUser);
    }

    public TokenDto login(AddUserDto addUserDto){
        Optional<AuthUser> user = authRepository.findByUserName(addUserDto.getUserName());
        if (!user.isPresent()){
            return null; // Ya esta presente el usuario no es necesario crearlo
        }
        if (passwordEncoder.matches(addUserDto.getPassword(), user.get().getPassword())){
            return new TokenDto(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDto validteToken(String token, RequestDto requestDto){
        if (!jwtProvider.validateToken(token, requestDto)){
            return null;
        }

        String username = jwtProvider.getUserNameFromToken(token);

        if (!authRepository.findByUserName(username).isPresent()){
            return null;
        }

        return new TokenDto(token);
    }
}
