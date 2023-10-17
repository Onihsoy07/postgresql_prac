package com.test.postgresql_test.config.jwt;

import com.test.postgresql_test.config.auth.PrincipalDetails;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.JwtTokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.tokenValidityInMilliseconds}")
    private long tokenValidityInMilliseconds;
    private Key key;


    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public JwtTokenDto generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + tokenValidityInMilliseconds);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 10000000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtTokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Set<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());

        // UserDetails 객체를 만들어서 Authentication 리턴
        PrincipalDetails principal = new PrincipalDetails(Users.builder()
                .username(claims.getSubject())
                .password("")
                .build());
        //new User(claims.getSubject(), "", authorities)
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}


















//    @Override
//    public void afterPropertiesSet() throws Exception {
//        byte[] keyBytes = Base64.getDecoder().decode(secret);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }

//    /**token 생성 algorithm */
//    public String createToken(Authentication authentication){
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        long now = (new Date()).getTime();
//        Date validity = new Date(now + this.tokenValidityInMilliseconds);
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim(AUTHORITIES_KEY, authorities)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .setExpiration(validity)
//                .compact();
//    }
//
//    /**인증 정보 조회 */
//    public Authentication getAuthentication(String token) {
//        Claims claims = getJwtClaims(token);
//
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        User principal = new User(claims.getSubject(), "", authorities);
//
//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
//    }
//
//    /**token 유효성 검증 */
//    public boolean validateToken(String token){
//        try{
//            getJwtClaims(token);
//            return true;
//        }catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
//            log.info("잘못된 JWT 서명입니다.");
//        }catch(ExpiredJwtException e){
//            log.info("만료된 JWT 토큰입니다.");
//        }catch(UnsupportedJwtException e){
//            log.info("지원하지 않는 JWT 토큰입니다.");
//        }catch(IllegalArgumentException e){
//            log.info("JWT 토큰이 잘못되었습니다.");
//        }
//        return false;
//    }
//
//    private Claims getJwtClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUsername
//                ((String) authentication.getPrincipal());
//
//
//        return new UsernamePasswordAuthenticationToken(
//                principalDetails.getUsername(),
//                principalDetails.getPassword(),
//                principalDetails.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
