package com.minsait.teste.usuarioApi.domain.service.util;

import com.minsait.teste.usuarioApi.domain.exception.NegocioException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {
    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new NegocioException("O formato do e-mail deve ser válido.");
        }
        return true;
    }
}
