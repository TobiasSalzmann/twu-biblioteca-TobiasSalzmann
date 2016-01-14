package com.twu.biblioteca;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by tsalzman on 1/7/16.
 */
public class Command implements Consumer<String[]>{

    private String name;
    private String[] paramNames;
    private String description;
    private Consumer<String[]> effect;

    public static Command createCommand(Consumer<String[]> effect, String name, String description, String... paramNames) {
        return new Command(effect, name, description, paramNames);
    }

    public int getNumArgs(){
        return paramNames.length;
    }

    @Override
    public void accept(String[] args){
        effect.accept(args);
    }

    private Command(Consumer<String[]> effect, String name, String description, String... paramNames) {
        this.name = name;
        this.paramNames = paramNames;
        this.description = description;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        if(paramNames.length == 0)
            return(name + " - " + description);
        else
            return(name + " " + String.join(" ", paramNames) + " - " + description);
    }


}
