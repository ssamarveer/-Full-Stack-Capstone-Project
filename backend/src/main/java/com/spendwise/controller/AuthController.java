package com.spendwise.controller;
import com.spendwise.model.AppUser; import com.spendwise.repo.UserRepository; import com.spendwise.security.JwtService; import jakarta.validation.constraints.*; import org.springframework.http.*; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api/auth") public class AuthController {
 private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthController(UserRepository u,PasswordEncoder e,JwtService j){users=u;encoder=e;jwt=j;}
 public record RegisterRequest(@NotBlank String name,@Email @NotBlank String email,@Size(min=6) String password){}
 public record LoginRequest(@Email @NotBlank String email,@NotBlank String password){}
 @PostMapping("/register") public ResponseEntity<?> register(@RequestBody RegisterRequest r){if(users.findByEmail(r.email().toLowerCase()).isPresent())return ResponseEntity.badRequest().body(Map.of("message","Email already registered"));AppUser u=new AppUser();u.setName(r.name());u.setEmail(r.email().toLowerCase());u.setPassword(encoder.encode(r.password()));users.save(u);return ResponseEntity.ok(payload(u));}
 @PostMapping("/login") public ResponseEntity<?> login(@RequestBody LoginRequest r){var u=users.findByEmail(r.email().toLowerCase()).orElse(null);if(u==null||!encoder.matches(r.password(),u.getPassword()))return ResponseEntity.status(401).body(Map.of("message","Invalid email or password"));return ResponseEntity.ok(payload(u));}
 private Map<String,Object> payload(AppUser u){return Map.of("token",jwt.generate(u.getEmail()),"name",u.getName(),"email",u.getEmail());}
}
