package org.example;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void asercionesListas(){
        List<Integer> numeros = Arrays.asList(1,34,544,2,4,32,11);
        //assertThat(numeros, is(empty())); //la lista sea vacia

        assertThat(numeros,hasSize(7)); //contenga 7 elementos

        assertThat(numeros, contains(1,34,544,2,4,32,11)); //misma cantidad de
        // elementos en el mismo orden, esto es lo que yo espero
        assertThat(numeros, containsInAnyOrder(34,544,2,4,1,32,11)); //esten todos los valores

        assertThat(numeros, hasItem(544));//que contenga ese valor
        assertThat(numeros, not(hasItem(22)));
        assertThat(numeros, hasItems(544,34,2));

        assertThat(numeros, everyItem(greaterThan(0))); //cada elemento mayor a 0

        //ANY OF -> Multiples comparaciones si se cumple una el test pasa
        assertThat(numeros, anyOf(hasItem(4),hasItem(11),hasItem(40)));
    }

    @Test
    void asercionesArray(){
        String[] arrayNombres = {"Claudia", "Glow", "Por ejemplo", "Oscar", "Vida"};

        assertThat(arrayNombres, arrayWithSize(5));
        assertThat(arrayNombres, arrayWithSize(greaterThanOrEqualTo(3)));

        //assertThat("El array no esta vacio",arrayNombres, emptyArray());

        assertThat(arrayNombres,arrayContaining("Claudia", "Glow", "Por ejemplo", "Oscar", "Vida"));
        assertThat(arrayNombres,arrayContainingInAnyOrder("Por ejemplo", "Oscar", "Vida","Claudia", "Glow"));

        assertThat(arrayNombres, hasItemInArray("Oscar"));
        assertThat(arrayNombres, hasItemInArray("Vida"));

        assertThat(arrayNombres, allOf(hasItemInArray("Oscar"),hasItemInArray("Vida")));

    }

    @Test
    void asercionesMap(){
        Map<String, Integer> edadEstudiante = new HashMap<>();
        edadEstudiante.put("Juana", 28);
        edadEstudiante.put("Oscar", 30);
        edadEstudiante.put("Lobas", 19);
        edadEstudiante.put("Jaz", 21);
        edadEstudiante.put("Clara", 20);
        edadEstudiante.put("Huevo", 22);

        assertThat(edadEstudiante, not(anEmptyMap()));

        assertThat(edadEstudiante.keySet(), hasSize(6));
        assertThat(edadEstudiante.keySet(), hasSize(greaterThan(0)));

        assertThat(edadEstudiante, hasKey("Lobas"));

        assertThat(edadEstudiante, allOf(hasKey("Lobas"),hasKey("Huevo")));

        assertThat(edadEstudiante, hasValue(22));
        assertThat(edadEstudiante, hasEntry("Jaz", 21));

        assertThat(edadEstudiante.values(), everyItem(greaterThanOrEqualTo(18)));
    }

    @Test
    void asersionObjeto(){
        Persona usuario = new Persona("Juan","Marcos",22,"Facatativa",true);

        assertThat(usuario,not(nullValue()));

        assertThat(usuario, hasProperty("edad")); //que tenga esta propiedad en la clase
        assertThat(usuario, hasProperty("edad", greaterThanOrEqualTo(10)));
        //que el atributo ciudad debe ser string
        assertThat(usuario, hasProperty("ciudad",instanceOf(String.class)));

    }
}
