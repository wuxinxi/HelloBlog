package cn.xxstudy.api.utils

import cn.xxstudy.api.configuration.properties.TokenProperties
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

/**
 * @date: 2024/7/7 11:45
 * @author: TangRen
 * @remark:
 */
@Component
class TokenUtil {

    @Autowired
    private lateinit var tokenProperties: TokenProperties

    fun generateToken(id: Int, email: String): String {
        return JWT.create()
            .withClaim("id", id)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + tokenProperties.expireTime))
            .sign(Algorithm.HMAC256(tokenProperties.secret))
    }

    fun parseToken(token: String): Map<String, Any>? {
        return try {
            val jwtVerifier = JWT.require(Algorithm.HMAC256(tokenProperties.secret)).build()
            val decodedJWT = jwtVerifier.verify(token)
            val map = HashMap<String, Any>()
            map["id"] = decodedJWT.getClaim("id").asInt()
            map["email"] = decodedJWT.getClaim("email").asString()
            map;
        } catch (e: Exception) {
            println("parseToken exception => ${e.message},token=$token")
            null
        }
    }

}