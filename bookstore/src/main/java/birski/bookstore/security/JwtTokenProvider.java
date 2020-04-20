package birski.bookstore.security;

import birski.bookstore.models.daos.CustomUser;
import birski.bookstore.models.dtos.CustomUserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static birski.bookstore.configs.SecurityConstants.EXPIRATION_TIME;
import static birski.bookstore.configs.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {

    //Generate token
    public String generateToken(Authentication authentication){
        //TODO prawdopodobnie będę zmieniał na CustomUser jeśli nie zadziała
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        String username = customUser.getUsername();
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", customUser.getUsername());
        claims.put("firstName", customUser.getFirstName());
        claims.put("lastName", customUser.getLastName());
        claims.put("address", customUser.getAddress());
        //w przyszłości tu będę dodawał role
        return Jwts.builder().setSubject(username).setClaims(claims).setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }
    //Validate the token
    //Get user Id from token

}
