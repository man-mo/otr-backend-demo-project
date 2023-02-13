package com.daimler.otr.demo.account.jwt;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JwtTokenHelper implements Serializable {

    public static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";
    private static final String ISSUER = "PGS Authorization Center";
    private static final String PRIVILEGE_SEPARATOR = ",";
    private static final String CLAIM_KEY_USER_ID = "uid";
    private static final String CLAIM_KEY_USER_NAME = "unm";
    private static final String CLAIM_KEY_ROLE = "rol";
    private static final String CLAIM_KEY_PRIVILEGES = "pri";
    private static final String CLAIM_KEY_FULL_NAME = "fnm";
    private static final String CLAIM_KEY_GROUPS = "grp";
    private final static String DEFAULT_JWT_SECRET = "7283052a318d17ff5e1a0b4952173d62060cc2ae";
    private final static Long DEFAULT_JWT_EXPIRATION_IN_SECONDS = 86400L;
    private static final long serialVersionUID = 4874018310650853279L;

    private static String secret = DEFAULT_JWT_SECRET;
    private static Long expiration = DEFAULT_JWT_EXPIRATION_IN_SECONDS;

    public static boolean isJwtToken(String authorization) {
        return hasText(authorization) && authorization.startsWith(BEARER);
    }

    public static String getJwtToken(String authorization) {
        if (isJwtToken(authorization)) {
            return authorization.substring(BEARER.length() + 1);
        }

        return null;
    }

    public static String generateToken(JwtUser user) {
        return generateToken(user, null);
    }

    public static String generateToken(JwtUser user, Date expireTime) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, user.getId());
        claims.put(CLAIM_KEY_USER_NAME, user.getUsername());
        claims.put(CLAIM_KEY_ROLE, user.getRoleCode());
        claims.put(CLAIM_KEY_FULL_NAME, user.getName());
        claims.put(CLAIM_KEY_PRIVILEGES, StringUtils.join(user.getPrivileges(), PRIVILEGE_SEPARATOR));
        claims.put(CLAIM_KEY_GROUPS, user.getGroups());

        return generateToken(claims, expireTime);
    }

    public Boolean validateToken(String token) {
        Claims claims = getClaims(token);
        return claims != null && !isTokenExpired(claims);
    }

    public static JwtUser parseUserByAuthorizeToken(String authorizeToken) {
        String token = JwtTokenHelper.getJwtToken(authorizeToken);
        return JwtTokenHelper.parseUser(token);
    }

    public static JwtUser parseUser(String token) {
        Claims claims = getClaims(token);
        JwtUser.JwtUserBuilder builder = JwtUser.builder();

        getClaimValue(CLAIM_KEY_USER_NAME, claims).ifPresent(builder::username);
        getClaimValue(CLAIM_KEY_ROLE, claims).ifPresent(builder::roleCode);
        getClaimValue(CLAIM_KEY_FULL_NAME, claims).ifPresent(builder::name);
        getClaimValue(CLAIM_KEY_USER_ID, claims).ifPresent(userId -> builder.id(Integer.parseInt(userId)));
        builder.groups(getGroupDTOS(claims));

        String privilegeString = getClaimValue(CLAIM_KEY_PRIVILEGES, claims).orElse("");
        List<String> privileges = Arrays.asList(StringUtils.split(privilegeString, PRIVILEGE_SEPARATOR));
        builder.privileges(privileges);
        return builder.build();
    }

    private static List<GroupDTO> getGroupDTOS(Claims claims) {
        String groupsString = getClaimValue(CLAIM_KEY_GROUPS, claims).orElse("");
        if (StringUtils.isEmpty(groupsString)) {
            return Collections.emptyList();
        }

        Gson gson = new Gson();
        GroupDTO[] array = gson.fromJson(groupsString, GroupDTO[].class);
        return Arrays.stream(array)
                     .collect(Collectors.toList());
    }

    private static String generateToken(Map<String, Object> claims, Date expireTime) {
        setJwtInfo();

        if (expireTime == null) {
            expireTime = generateExpirationDate();
        }

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuer(ISSUER)
                   .setIssuedAt(new Date())
                   .setExpiration(expireTime)
                   .signWith(SignatureAlgorithm.HS512, secret)
                   .compact();
    }

    private static void setJwtInfo() {
        if (secret == null) {
            secret = DEFAULT_JWT_SECRET;
        }
        if (expiration == null) {
            expiration = DEFAULT_JWT_EXPIRATION_IN_SECONDS;
        }
    }

    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(Claims claims) {
        final Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    private static Optional<String> getClaimValue(String key, Claims claims) {
        String value = null;
        if (claims.containsKey(key)) {
            Object v = claims.get(key);
            if (v != null && StringUtils.isNotEmpty(v.toString())) {
                value = v.toString();
            }
        }
        return Optional.ofNullable(value);
    }

    @SuppressWarnings("PMD.NullAssignment")
    private static Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                         .setSigningKey(secret)
                         .parseClaimsJws(token)
                         .getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }
}
