package com.senla.security;

import com.senla.dto.token.TokenDto;
import com.senla.exception.MyAccessDeniedException;
import com.senla.property.JwtProperty;
import io.jsonwebtoken.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/** @author Aliaksei Kaspiarovich */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperty jwtProperty;

    /**
     * @param email user email
     * @param role user role
     * @return token
     */
    public TokenDto generateToken(String email, String role, String id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", role);
        claims.put("id", id);
        Date expirationDate =
                Date.from(
                        LocalDate.now()
                                .plusDays(jwtProperty.getExpiration())
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant());
        return TokenDto.builder()
                .token(
                        jwtProperty.getBearer()
                                + Jwts.builder()
                                        .setClaims(claims)
                                        .setSubject(email)
                                        .setExpiration(expirationDate)
                                        .signWith(SignatureAlgorithm.HS512, jwtProperty.getSecret())
                                        .compact())
                .role(role)
                .build();
    }

    /**
     * @param token token from request
     * @return {@literal true}, if everything is ok, {@literal false} otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperty.getSecret()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token expired", e);
            throw new MyAccessDeniedException("Token expired");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported jwt", e);
            throw new MyAccessDeniedException("Unsupported jwt");
        } catch (MalformedJwtException e) {
            log.error("Malformed jwt", e);
            throw new MyAccessDeniedException("Malformed jwt");
        } catch (SignatureException | IllegalArgumentException e) {
            log.error("Invalid token", e);
            throw new MyAccessDeniedException("Invalid token");
        }
    }

    /**
     * @param token token from request
     * @return email
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperty.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<SimpleGrantedAuthority> getRoleFromToken(String token) {
        Claims claims =
                Jwts.parser()
                        .setSigningKey(jwtProperty.getSecret())
                        .parseClaimsJws(token)
                        .getBody();
        Function<Claims, String> claimsListFunction =
                cl -> {
                    return (String) cl.get("authorities");
                };
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(claimsListFunction.apply(claims)));
        return authorities;
    }

    public UUID getIdFromToken(String token) {
        Claims claims =
                Jwts.parser()
                        .setSigningKey(jwtProperty.getSecret())
                        .parseClaimsJws(token)
                        .getBody();
        Function<Claims, String> claimsFunction =
                cl -> {
                    return (String) cl.get("id");
                };
        return UUID.fromString(claimsFunction.apply(claims));
    }

    /**
     * @param request HttpServletRequest request
     * @return token, if everything is ok, {@literal null} otherwise
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(jwtProperty.getAuthorization());
        if (StringUtils.hasText(token) && token.startsWith(jwtProperty.getBearer())) {
            return token.substring(7);
        }
        return null;
    }
}
