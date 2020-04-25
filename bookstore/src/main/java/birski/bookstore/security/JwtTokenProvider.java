package birski.bookstore.security;

import birski.bookstore.models.daos.CustomUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static birski.bookstore.configs.SecurityConstants.EXPIRATION_TIME;
import static birski.bookstore.configs.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    //Generate token
    public String generateToken(Authentication authentication){
        //TODO prawdopodobnie będę zmieniał na CustomUser jeśli nie zadziała
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        String username = customUser.getUsername();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", customUser.getId());
        claims.put("username", customUser.getUsername());
        //w przyszłości tu będę dodawał role
        return Jwts.builder().setSubject(username).setClaims(claims).setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    //Validate the token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException exception){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException exception){
            System.out.println("Invalid JWT Token");
        }catch (ExpiredJwtException exception){
            System.out.println("Expired JWT token");
        }catch (UnsupportedJwtException exception){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException exception){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    //Get user Id from token
    public Long getIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        logger.info(claims.get("id").toString());
        String id = claims.get("id").toString();
        logger.info(id);
        return Long.parseLong(id);
    }



}
