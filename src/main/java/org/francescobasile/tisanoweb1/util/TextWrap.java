package org.francescobasile.tisanoweb1.util;

public class TextWrap {

    private static String[] decorators = new String[]{"\t\t****** ", " ******"};

    /**
     * Decommentre l'esempio all'interno della classe dove si vuole customizzare i delimitatori
     * di testo. Utile per il logger per evidneziarne i messaggi in console dirante il testing.
     * Di default il testo di messaggi viene avvolto in asterischi.
     * Aggiungere l'import statico per la funzione setter static.
     * esempio:
     * static {setDecorators(new String[]{"\n ********* ", " *********"});}
     *
     * @param decorators Stringhe delimitatori del testo da avvolgere
     */
    public static void setDecorators(String[] decorators) {
        TextWrap.decorators = decorators;
    }

    public static String leadAndTrail(String msg) {
        return decorators[0] + msg + decorators[1];
    }

    public static String lead(String msg) {
        return decorators[0] + msg;
    }

    public static String trail(String msg) {
        return msg + decorators[1];
    }

}
