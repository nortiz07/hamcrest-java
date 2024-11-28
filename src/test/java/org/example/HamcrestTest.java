package org.example;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestTest {

    @Test
    public void pruebasCalculadora(){
        assertThat("Imprime si falla uno",Calculadora.add(2,3),is(5));
        assertThat("El resultado de la suma esta mal",Calculadora.add(2,3),is(equalTo(5)));
        assertThat("Imprime si falla tres",Calculadora.add(2,3),equalTo(6));
    }

    @Test
    void asercionesNumericas(){
        assertThat(Calculadora.add(2,3),not(is(equalTo(4))));//cualquier valor menos 4
        assertThat(10,greaterThan(5));//10 es mayor que 5
        assertThat(Calculadora.add(2,3),greaterThanOrEqualTo(5));//la suma es mayor o igual a 5
        assertThat(10,lessThan(20));//10 es menor que 20
        assertThat(Calculadora.add(2,3),lessThanOrEqualTo(5));

        assertThat(10.5,closeTo(10.0,0.5));
        assertThat(10.6,closeTo(10.0,0.5));
    }

    @Test
    void asercionesText(){
        String valorEncontrado = "Hello Word";
        assertThat(valorEncontrado, is("Hello Word"));
        //ignorando mayusculas y minusculas
        assertThat(valorEncontrado, equalToIgnoringCase("hello Word"));
        //assertThat("",is(not(emptyString())));//no es una cadena vacia
        assertThat(valorEncontrado, startsWith("Hello"));//contenga esa palabra al inicio
        assertThat(valorEncontrado,endsWith("Hello"));//contenga esa palabra al final
        assertThat(valorEncontrado, containsString("llo wor"));//contenga eso en su estructura
        assertThat(valorEncontrado, hasLength(12));
    }

    @Test
    void asercionesCadenas(){
        assertThat("La vaca lola, la vaca lola", endsWithIgnoringCase("lola"));
        assertThat("La vaca lola, la vaca lola", startsWithIgnoringCase("la"));

        //Iguala textos no importa los espacio
        assertThat("La vaca lola", equalToCompressingWhiteSpace(" La vaca lola"));
        //En ese orden compara
        assertThat("La vaca lola", stringContainsInOrder("vaca","lola"));
        //Todos en mayuscula
        assertThat("HELLO",matchesRegex("[A-Z]+"));
    }

    @Test
    void asercionesCmbinadas(){
        String mensaje = "Hola, buen dia";
        //comparador, se deben cumplir todas
        Matcher<String> matcherAllOf = allOf(containsString("buen dia"), endsWith("a"));
        assertThat(mensaje,matcherAllOf);

        //se debe cumplir alguna
        Matcher<String> matcherAnyOf = anyOf(startsWithIgnoringCase("Buen"),
                startsWithIgnoringCase("hola"));
        assertThat(mensaje,matcherAnyOf);
    }

    @Test
    void asercionesCombinada(){
        String mensaje = "La asercion es muy importante";
        Matcher<String> matcherEither = either(startsWithIgnoringCase("la")).or(endsWith("mundo"));
        assertThat(mensaje,matcherEither);

        Matcher<String> matcherBoth = both(endsWithIgnoringCase("te"))
                .and(containsStringIgnoringCase("asercion"));
        assertThat(mensaje,matcherBoth);

        //El and se debe cumplir
        Matcher<String> matcherEitherAnd = either(startsWithIgnoringCase("la")).or(endsWith("mundo"))
                .and(containsStringIgnoringCase("MUY"));
        assertThat(mensaje,matcherEitherAnd);
    }

}
