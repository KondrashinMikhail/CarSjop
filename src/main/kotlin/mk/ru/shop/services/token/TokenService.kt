package mk.ru.shop.services.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.Date
import mk.ru.shop.configurations.JwtProperties
import mk.ru.shop.utils.Jwt
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class TokenService(jwtProperties: JwtProperties) {
    private val secretKey = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray())

    fun generateToken(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String = Jwt.PREFIX +
            Jwts.builder()
                .claims()
                .subject(userDetails.username)
                .issuedAt(Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .add(additionalClaims)
                .and()
                .signWith(secretKey)
                .compact()

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean =
        userDetails.username ==
                getLoginFromToken(token) &&
                getClaims(token).expiration.after(Date(System.currentTimeMillis()))

    private fun getClaims(token: String): Claims = Jwts.parser()
        .verifyWith(secretKey)
        .build()
        .parseSignedClaims(token)
        .payload

    fun getLoginFromToken(token: String): String = getClaims(token).subject
}