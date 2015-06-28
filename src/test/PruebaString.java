/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.morethansimplycode.formatting.Formattable;
import com.morethansimplycode.formatting.CustomFormatter;
import com.morethansimplycode.formatting.StringFormatter;

/**
 *
 * @author Oscar
 */
public class PruebaString {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println(new StringFormatter().appendFormat("{0} {1} {2:K V N}", "hola", "pepe", new Pruebas("clave", "valor", "number")));
    }
}

class Pruebas implements Formattable {

    public String key;
    public String valor;
    public String number;

    public Pruebas(String key, String valor, String number) {
        this.key = key;
        this.valor = valor;
        this.number = number;
    }

    @Override
    public String toString(String format) {

        return PruebasFormatter.getInstance().format(format, this);
    }

    @Override
    public String toString() {

        return "Esto es una prueba " + this.key;
    }
}

class PruebasFormatter implements CustomFormatter {

    private static final PruebasFormatter instance;

    static {
        instance = new PruebasFormatter();
    }

    public static PruebasFormatter getInstance() {
        return instance;
    }

    @Override
    public String format(String fmt, Object arg) {

        if (!(arg instanceof Pruebas))
            return arg.toString();

        Pruebas p = (Pruebas) arg;

        if (fmt != null) {

            fmt = fmt.replaceAll("K", p.key);
            fmt = fmt.replaceAll("V", p.valor);
            fmt = fmt.replaceAll("N", p.number);
            return fmt.trim();
        }

        return arg.toString();
    }
}
