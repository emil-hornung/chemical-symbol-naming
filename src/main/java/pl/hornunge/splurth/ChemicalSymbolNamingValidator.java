package pl.hornunge.splurth;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ChemicalSymbolNamingValidator {

    private static final int SYMBOL_LENGTH = 2;

    public boolean symbolFollowsNamingRules(String name, String symbol) {
        checkNotNull(symbol, "Symbol mustn't be null.");
        checkArgument(symbol.length() == SYMBOL_LENGTH, "The length of symbol must be %s.", SYMBOL_LENGTH);
        checkArgument(containsOnlyLettersAtoZ(name), "Name must only contains letter a to z.");
        checkArgument(containsOnlyLettersAtoZ(symbol), "Symbol must only contains letter a to z.");

        name = name.toLowerCase();
        symbol = symbol.toLowerCase();

        return firstLetterOfSymbolAppearsInName(name, symbol) &&
                secondLetterOfSymbolAppearsInNameAfterFirstLetterOfSymbol(name, symbol);
    }

    private boolean secondLetterOfSymbolAppearsInNameAfterFirstLetterOfSymbol(String name, String symbol) {
        int indexOfFirstLetterOfSymbolInName = indexOfFirstLetterOfSymbolInName(name, symbol);
        char secondLetterOfSymbol = symbol.charAt(1);
        return name.indexOf(secondLetterOfSymbol, indexOfFirstLetterOfSymbolInName + 1) > -1;
    }

    private boolean firstLetterOfSymbolAppearsInName(String name, String symbol) {
        return indexOfFirstLetterOfSymbolInName(name, symbol) > -1;
    }

    private int indexOfFirstLetterOfSymbolInName(String name, String symbol) {
        return name.indexOf(symbol.charAt(0));
    }

    private boolean containsOnlyLettersAtoZ(String string) {
        return string.chars().allMatch(charAsInt -> isLetterAtoZ((char)charAsInt));
    }

    private boolean isLetterAtoZ(char character){
        char characterInLowerCase = Character.toLowerCase(character);
        return characterInLowerCase >= 'a' && characterInLowerCase <= 'z';
    }

}