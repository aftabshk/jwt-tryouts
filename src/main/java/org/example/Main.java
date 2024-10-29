package org.example;

import io.jsonwebtoken.Jwts;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JWTService jwtService = new JWTService();

        KeyPair keyPair = Jwts.SIG.RS256.keyPair().build();

        System.out.println("------- Private Key -------");
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        System.out.println("------- Public Key -------");
        System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        System.out.println("------- JWT Token -------");
        String jwtToken = jwtService.generateToken(Map.of("name", "aftab"), keyPair);
        System.out.println(String.format("Jwt token - %s", jwtToken));

        System.out.println(jwtService.verifyToken(jwtToken, keyPair.getPublic()));
    }

    public static void main2(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxpM2SeYdpr59qLhav2MTngLrzF4anjKuxpjSIrqbzg9aOTYLdq1Cssv86XRb8Kay62jELeBFBcok52QqJBM1fiZLyU+54CS6if24rLkuO94tHR599b1qPa1t7NAvJckDrNDj2E1QFtwliy3juzhU8G4I1JqM1KlIEsR6zz+lgHHiJAD/hB4hePi3ad3gmapbFdCZvYSE1nTnCITVpb/qhIEYTSuRd9JCjz5ViEms9dvSrMXydUz9oiL3j1qYAaVqoUxAg+v3tiZU8/rrpAqGKKskpKMO8apaYTF9lk7X+yloju2eXA2bDEG9tPzoGWa4AIlP3sINYboTjzWMvYq3hAgMBAAECggEAHvUmchlFtPBLKrjfcUOohOOVMNnvuHE2Vp4tV4iARb26/U+at8CsLXyAKA6i+8zTcjimBWamMy6fpXoicOBqXfTeq+Zej4KiIuVsMJA/0KlpF0HvFG/8azP9XEokp7WGhLa0GoYhxWExfIsTByLY5oGwvkx+crxuUK0BObrI49unK1ErrHyDPdo6x1vThxviDjXlQxM0I9f9skSCmcM1pRwW3Wz+psAR4n1hA7VFG+NH0kDoFmHESBRJSXahz2DfL7KSzDst1zI8JQElVkTOYqXvOaUmlfbw0513dIbZG4mx6IrX+F6ehTP9DdoxXgISR/ZTwjHBhkImSyqM5STXNQKBgQD3b38I2qEitPmJN1yxCBn97q/WR/9QmjpAbK0K4SIFMprwD5iwyhxxZipW1Rm0I/C0RBchfbLltAlVt7d+jjhHEVUIfKae6wZP147vPcXdoR13HqffKLcIPE9YwRkpBm6G9QkH3im78+C+d32k9DdBnuWQc5Q+rcIPp1aWjr50/wKBgQC3yuNkZnz/VEhqxW47GCQ7/plgyUvpJnIGTmNylw7I12fn5rHGG+5X5JuYOwQ32NlOG9FMXjv7+Dj+YT8oB1MoJ2TnJQ7+3TZ4XLu0/4lR2nmnfvCxZrmz0xJoe8Q8iXoTMaOAWnK34ZPVxMvu3DeIGzMhsQpo7wBd5S8jPs59HwKBgDxEuSGT9ym4AQvuIzKvsX0LlWLROJwOZN/qWJ/MxHeVou2ULvHo2NK6OtfZoiAhnPCboTK0Jk4S8y8gx54Y0BZnX1no2sDcqDXsYFFfr4FTTBUGr4cIqMRlxEHvDszSVzuiFagH8gxo07Yw7rauEYN3S51KubImqp0beljDvhpLAoGBAKqPs3TSE8AT5FyJueFHZyDyBj6msDa/QeQOTQXjn4nNNRUlpubVttuEQM0GP8TgDdIJyLMZZItR8PpJHwYzvf0uidpkLsVgXxNV47B10GbQ3EMPb6kCTuXNxHCIIzWW014Km9QanAl/YuVoC2UwoqxBqbLjjGiTHAEHFjacK547AoGAUm5zAnMfp7Rgre1/Ge2iGuvZCFRXqlF7mwQhUmncmej3h901UBUlzH1sZwp3XJEC17PbuY4X7Xw4Y+CPLq9J8SKgHX62SJTXdytQzn4NLmUwP+cA8Ehl+tMjQ4K3XtsSlsTZADwqhxdKK2Oli/6OYVZFgkvBySfgHN9j3gnuUOg=");

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decoded);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(pkcs8EncodedKeySpec);

        String jwtToken = new JWTService().generateToken(Map.of("name", "tab"), privateKey);

        System.out.println(String.format("Jwt token - %s", jwtToken));
    }
}