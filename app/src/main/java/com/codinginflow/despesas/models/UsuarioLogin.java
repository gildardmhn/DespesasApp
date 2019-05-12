package com.codinginflow.despesas.models;

import android.util.Patterns;

public class UsuarioLogin {

    private String email;
    private String senha;

    public UsuarioLogin(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isEmailValido() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isSenhaMaiorQue4() {
        return getSenha().length() > 4;
    }
}
