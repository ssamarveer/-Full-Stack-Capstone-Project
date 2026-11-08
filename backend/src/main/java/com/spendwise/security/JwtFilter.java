package com.spendwise.security;
import com.spendwise.repo.UserRepository; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter; import jakarta.servlet.*; import jakarta.servlet.http.*; import java.io.IOException; import java.util.List;
@Component public class JwtFilter extends OncePerRequestFilter {
 private final JwtService jwt; private final UserRepository users;
 public JwtFilter(JwtService jwt,UserRepository users){this.jwt=jwt;this.users=users;}
 @Override protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException{
  String h=req.getHeader("Authorization"); if(h!=null&&h.startsWith("Bearer ")&&SecurityContextHolder.getContext().getAuthentication()==null){try{String email=jwt.extractEmail(h.substring(7)); if(users.findByEmail(email).isPresent()){var p=new UsernamePasswordAuthenticationToken(new User(email,"",List.of()),null,List.of()); SecurityContextHolder.getContext().setAuthentication(p);}}catch(Exception ignored){}}
  chain.doFilter(req,res);
 }
}
