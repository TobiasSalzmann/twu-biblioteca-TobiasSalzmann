package com.twu.biblioteca;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by tsalzman on 1/7/16.
 */
public class Util {




    public static <T> List<T> filter(Collection<T> coll, Predicate<T> p){
        return coll.stream().filter(p).collect(Collectors.toList());
    }

    public static <T1,T2> List<T2> map(Collection<T1> coll, Function<T1,T2> f){
        return coll.stream().map(f).collect(Collectors.toList());
    }

    public static <T> Optional<T> find(List<T> list, Predicate<T> p){
        return list.stream().filter(p).findAny();
    }

    public static String simplify(String toSimplify){
        return toSimplify.replaceAll("\\s+"," ").trim().toLowerCase();
    }

}
