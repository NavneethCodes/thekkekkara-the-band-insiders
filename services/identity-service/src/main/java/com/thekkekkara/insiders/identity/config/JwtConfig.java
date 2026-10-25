
package com.thekkekkara.insiders.identity.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

@Configuration
public class JwtConfig {

    @Bean
    public RSAKey rsaKey(
            @Value("${JWT_PRIVATE_KEY_PATH}") String privateKeyPath,
            @Value("${JWT_PUBLIC_KEY_PATH}") String publicKeyPath)
            throws Exception {

        String privateKeyPem = Files.readString(Path.of(privateKeyPath));
        String publicKeyPem = Files.readString(Path.of(publicKeyPath));

        String privateKeyBase64 = privateKeyPem
                .replaceAll("-----BEGIN [^-]+-----", "")
                .replaceAll("-----END [^-]+-----", "")
                .replaceAll("\\s", "");

        String publicKeyBase64 = publicKeyPem
                .replaceAll("-----BEGIN [^-]+-----", "")
                .replaceAll("-----END [^-]+-----", "")
                .replaceAll("\\s", "");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(
                new PKCS8EncodedKeySpec(
                        Base64.getDecoder().decode(privateKeyBase64)));

        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(
                new X509EncodedKeySpec(
                        Base64.getDecoder().decode(publicKeyBase64)));

        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .build();
    }

    @Bean
    public JwtEncoder jwtEncoder(RSAKey rsaKey) {
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(rsaKey));

        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder(
            RSAKey rsaKey,
            @Value("${JWT_ISSUER}") String jwtIssuerString) throws JOSEException {

        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder
                .withPublicKey(rsaKey.toRSAPublicKey())
                .build();

        jwtDecoder.setJwtValidator(
                org.springframework.security.oauth2.jwt.JwtValidators
                        .createDefaultWithIssuer(jwtIssuerString));

        return jwtDecoder;
    }
}
