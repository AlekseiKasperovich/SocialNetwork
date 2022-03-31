package com.senla.security;

import com.senla.api.dto.сonstants.Constants;
import com.senla.api.exception.MyAccessDeniedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author Aliaksei Kaspiarovich
 */
@Slf4j
@Service
public class JwtTokenProvider {

    //ToDo  собрать секьюрные проперти в один класс пропертей и юзать оттуда
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.days}")
    private long expirationDays;

    @Value("${jwt.authorization.header}")
    private String authorizationHeader;

    /**
     *
     * @param email user email
     * @return token
     */
    public String generateToken(String email) {
        Date expirationDate = Date.from(
                LocalDate.now()
                        .plusDays(expirationDays)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     *
     * @param token token from request
     * @return {@literal true}, if everything is ok, {@literal false} otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
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
     *
     * @param token token from request
     * @return email
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     *
     * @param request HttpServletRequest request
     * @return token, if everything is ok, {@literal null} otherwise
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(authorizationHeader);
        if (StringUtils.hasText(bearer) && bearer.startsWith(Constants.BEARER)) {
            return bearer.substring(7, bearer.length());
        }
        return null;
    }
}
