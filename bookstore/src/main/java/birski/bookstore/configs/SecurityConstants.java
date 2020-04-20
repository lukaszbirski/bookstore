package birski.bookstore.configs;

public class SecurityConstants {

    public static final String SING_UP_URLS = "/api/users/**";
    public static final String SING_UP_URLS_DTO = "/api/dto/users/**";
    public static final String SECRET = "SecretKeyToGenerateJWTs";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 30_000;  //30 seconds
}
