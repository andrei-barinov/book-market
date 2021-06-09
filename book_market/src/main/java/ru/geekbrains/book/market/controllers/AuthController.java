package ru.geekbrains.book.market.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.book.market.beans.JwtTokenUtil;
import ru.geekbrains.book.market.jwt.JwtRequest;
import ru.geekbrains.book.market.jwt.JwtResponse;
import ru.geekbrains.book.market.entities.ErrorEntity;
import ru.geekbrains.book.market.services.UserService;

@RestController
@RequiredArgsConstructor
@Api("Set of endpoints for new user authentication")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    @ApiOperation("Creates token for user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", defaultValue = "ivanov_sergey@mail.com", required = true, dataTypeClass = String.class, type = "String", paramType = "query",
                    value = "Username"),
            @ApiImplicitParam(name = "password", defaultValue = "manager1", required = true, dataTypeClass = String.class, type = "String", paramType = "query",
                    value = "Password")})
    public ResponseEntity<?> createToken(@RequestBody JwtRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex){
            return new ResponseEntity<>(new ErrorEntity("Данные не совпадают", HttpStatus.UNAUTHORIZED.value()),  HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
