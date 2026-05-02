package br.com.carstore.service

import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import jakarta.inject.Singleton

@Slf4j
@Singleton
class TokenService {

    @Value("\${external.service.vehicle.url}")
    private String vehicleUrl

    @Value("\${external.service.vehicle.username}")
    private String username

    @Value("\${external.service.vehicle.password}")
    private String password

    private String cachedToken
    private long expiresAt = 0

    synchronized String getToken() {
        if (cachedToken == null || System.currentTimeMillis() >= expiresAt) {
            refreshToken()
        }
        return cachedToken
    }

    private void refreshToken() {
        log.info("Obtaining new JWT token from vehicle-service...")
        def client = HttpClient.create(new URL(vehicleUrl))
        try {
            def request = HttpRequest.POST("/login", [username: username, password: password])
            def response = client.toBlocking().exchange(request, Map)
            def body = response.body()
            cachedToken = body.access_token as String
            def expiresIn = body.expires_in as Long
            expiresAt = System.currentTimeMillis() + ((expiresIn - 30) * 1000)
            log.info("JWT token obtained successfully")
        } finally {
            client.close()
        }
    }
}
