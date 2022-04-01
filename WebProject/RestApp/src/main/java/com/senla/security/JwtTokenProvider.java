package com.senla.security;

import com.senla.api.exception.MyAccessDeniedException;
import com.senla.property.JwtProperty;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Aliaksei Kaspiarovich
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperty jwtProperty;

    /**
     * @param email user email
     * @return token
     */
    public String generateToken(String email) {
        Date expirationDate = Date.from(
                LocalDate.now()
                        .plusDays(jwtProperty.getExpiration())
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperty.getSecret())
                .compact();
    }

    /**
     * @param token token from request
     * @return {@literal true}, if everything is ok, {@literal false} otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperty.getSecret())
                    .parseClaimsJws(token);
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

    /**
     * @param request HttpServletRequest request
     * @return token, if everything is ok, {@literal null} otherwise
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(jwtProperty.getAuthorization());
        if (StringUtils.hasText(token) && token.startsWith(jwtProperty.getBearer())) {
            return token.substring(7, token.length());
        }
        return null;
    }
}
