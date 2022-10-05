package com.project.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.entities.Role;
import com.project.entities.User;
import com.project.services.UserService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User>saveUser(@RequestBody User user) throws SendFailedException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
	@DeleteMapping("/user/save/{id}")
	public  ResponseEntity<?>deleteUser(@PathVariable("id") Long id) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save/{id}").toUriString());
     //  String x;
      //  x= ResponseEntity.created(uri).allow(userService.deleteUser(id));
        return ResponseEntity.created(uri).body(userService.deleteUser(id));

	  }
    

    //localhost:8082/api/role/addtouser?roleName=ROLE_ADMIN&username=wassim
    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestParam  String username,@RequestParam  String roleName ) {
        userService.addRoleToUser(username,roleName);
        return ResponseEntity.ok().build();
    }
    //localhost:8082/api/role/deletetouser?roleName=ROLE_ADMIN&username=wassim
    @PostMapping("/role/deletetouser")
    public ResponseEntity<?>deleteRoleToUser(@RequestParam  String username,@RequestParam  String roleName ) {
        userService.deleteRoleToUser(username,roleName);
        return ResponseEntity.ok().build();
    }
 // http://localhost:8089/SpringMVC/client/modify-client
  	@PutMapping("/modify-client/{id}")
  	public ResponseEntity <User> modifyClient(@PathVariable("id") Long id,@RequestBody User user) {
         URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/modify-client/**").toUriString());

  		return ResponseEntity.created(uri).body (userService.updateUser(id, user));
  	}

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
