package com.twu.biblioteca;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by tsalzman on 1/7/16.
 */
public class Util {
    public static <T> List<T> filterList(List<T> list, Predicate<T> p){
        return list.stream().filter(p).collect(Collectors.toList());
    }

    public static <T1,T2> List<T2> mapList(List<T1> list, Function<T1,T2> f){
        return list.stream().map(f).collect(Collectors.toList());
    }

}