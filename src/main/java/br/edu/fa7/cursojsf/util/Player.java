package br.edu.fa7.cursojsf.util;

/**
 * Created by jackson on 2/28/16.
 */
public class Player {
    private String name;
    private String code;

    public Player(String marca) {
        this.code = marca;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
