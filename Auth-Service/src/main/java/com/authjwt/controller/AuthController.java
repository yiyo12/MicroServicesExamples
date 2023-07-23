package com.authjwt.controller;

import com.authjwt.dto.AddUserDto;
import com.authjwt.dto.NewUserDto;
import com.authjwt.dto.RequestDto;
import com.authjwt.dto.TokenDto;
import com.authjwt.entity.AuthUser;
import com.authjwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {


    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AddUserDto addUserDto){

        TokenDto tokenDto = authService.login(addUserDto);
        if (addUserDto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);

    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validateDto(@RequestParam String token , @RequestBody RequestDto requestDto){
        TokenDto tokenDto = authService.validteToken(token, requestDto);

        if (tokenDto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/createUser")
    public ResponseEntity<AuthUser> addUserCreate(@RequestBody NewUserDto addUserDto){
    AuthUser authUser = authService.saveUser(addUserDto);
    if (addUserDto == null){
        return ResponseEntity.badRequest().build();
    }
    return  ResponseEntity.ok(authUser);
    }
}
