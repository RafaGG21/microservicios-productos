package com.commons.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TokenUtils {

	private static List<String> resetPasswordTokens = new ArrayList<>();


    public static void deleteToken(String token) {
        resetPasswordTokens.remove(token);
    }
	
    public static String generateToken() {
        String token = UUID.randomUUID().toString();
        resetPasswordTokens.add(token);
        return token;
    }

    public static boolean isTokenValid(String token) {
        // Verificar si el token ha expirado
        LocalDateTime expirationDate = getExpirationDate(token);
        LocalDateTime now = LocalDateTime.now();
        boolean tokenEnListaTokens = !resetPasswordTokens.isEmpty() ? 
        		resetPasswordTokens.stream().anyMatch(t -> t.equals(token)) : false;
        return tokenEnListaTokens && now.isBefore(expirationDate);
    }

    private static LocalDateTime getExpirationDate(String token) {
        // Establecer la fecha de expiración del token (por ejemplo, 24 horas después de su generación)
        LocalDateTime expirationDate = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
        return expirationDate;
    }
}
