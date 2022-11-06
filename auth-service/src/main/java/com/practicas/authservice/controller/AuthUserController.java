package com.practicas.authservice.controller;

import com.practicas.authservice.dto.AuthUserDto;
import com.practicas.authservice.dto.TokenDto;
import com.practicas.authservice.entity.AuthUser;
import com.practicas.authservice.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthUserController {

  @Autowired
  private AuthUserService authUserService;

  @PostMapping("/login")
  public ResponseEntity<TokenDto>login(@RequestBody AuthUserDto dto){
    TokenDto tokenDto = authUserService.login(dto);
    if (tokenDto == null)
         return ResponseEntity.badRequest().build();
    return ResponseEntity.ok(tokenDto);
  }

  @PostMapping("/validate")
  public ResponseEntity<TokenDto>validate(@RequestParam String token ){
    TokenDto tokenDto = authUserService.validate(token);
    if (tokenDto == null)
         return ResponseEntity.badRequest().build();
    return ResponseEntity.ok(tokenDto);
  }

  @PostMapping("/create")
  public ResponseEntity<AuthUser>create(@RequestBody AuthUserDto dto){
    AuthUser authUser = authUserService.save(dto);
    if (authUser == null)
         return ResponseEntity.ok(authUser);
    return ResponseEntity.ok(authUser);
  }
}
