package com.example.wayne.java8demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wayne on 2017/7/12.
 */

@SuppressWarnings("Convert2streamapi")
public class MethodRefsDemo {

    @FunctionalInterface
    interface Transformer<F, T> {
        T transform(F obj);
    }

    private static class Person {
        public int age;
        public String name;

        private Person(int age) {
            this.age = age;
            this.name = "Person-" + age;
        }

        public static Person convert(int age) {
            return new Person(age);
        }

        public int compareByAge(Person other) {
            return age - other.age;
        }

        @Override
        public String toString() {
            return "Person[name=" + name + ", age=" + age + "]";
        }
    }

    private <F, T> List<T> transform(List<F> list, Transformer<F, T> transformer) {
        ArrayList<T> result = new ArrayList<>(list.size());
        for (F obj : list) {
            result.add(transformer.transform(obj));
        }
        return result;
    }

    private Person createPerson(int age) {
        return Person.convert(age);
    }

    public void showUsage() {
        staticMethod();
        instanceMethod();
        typeMethod();
        constructorMethod();
    }

    private void staticMethod() {
        List<Integer> ageList = Arrays.asList(1, 2, 3, 4, 5);

//        Java 7
//        List<Person> personsList = transform(ageList, new Transformer<Integer, Person>() {
//            @Override
//            public Person transform(Integer obj) {
//                return Person.convert(obj);
//            }
//        });

//        Java 8 with Lambda
//        List<Person> personsList = transform(ageList, obj -> Person.convert(obj));

//        Java 8 with method references
        List<Person> personList = transform(ageList, Person::convert);

        System.out.println("staticMethod: ");
        System.out.println(Arrays.toString(personList.toArray()));
    }

    private void instanceMethod() {
        List<Integer> ageList = Arrays.asList(6, 7, 8, 9, 10);

        List<Person> personList = transform(ageList, this::createPerson);

        System.out.println("instanceMethod: ");
        System.out.println(Arrays.toString(personList.toArray()));
    }

    private void typeMethod() {
        List<Integer> ageList = Arrays.asList(1, 4, 2, 10, 6);
        List<Person> personList = transform(ageList, this::createPerson);

        Collections.sort(personList, Person::compareByAge);

        System.out.println("typeMethod: ");
        System.out.println(Arrays.toString(personList.toArray()));
    }

    private void constructorMethod() {
        List<Integer> ageList = Arrays.asList(11, 21, 31, 41, 51);

        List<Person> personList = transform(ageList, Person::new);

        System.out.println("constructorMethod: ");
        System.out.println(Arrays.toString(personList.toArray()));
    }
}
