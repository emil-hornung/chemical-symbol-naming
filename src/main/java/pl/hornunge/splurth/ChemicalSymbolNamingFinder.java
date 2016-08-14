package pl.hornunge.splurth;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ChemicalSymbolNamingFinder {
    public String findFirstValidSymbolFor(String name) {
        checkNotNull(name, "Name mustn't be null.");
        checkArgument(name.length() >= 2, "Name must have at least 2 characters.");

        name = name.toLowerCase();
        char firstLetterOfSymbol = leastLetterOf(withoutLastCharacter(name));
        char secondLetterOfSymbol = leastLetterOf(partOfNameAfterFirstLetterOfSymbol(name, firstLetterOfSymbol));
        return "" + Character.toUpperCase(firstLetterOfSymbol) + secondLetterOfSymbol;
    }

    private String partOfNameAfterFirstLetterOfSymbol(String name, char firstLetterOfSymbol) {
        int indexOfFirstLetterOfSymbol = name.indexOf(firstLetterOfSymbol);
        return name.substring(indexOfFirstLetterOfSymbol + 1, name.length());
    }

    private char leastLetterOf(String string) {
        char leastLetter = string.charAt(0);
        for (char character:string.toCharArray()) {
            if (character < leastLetter){
                leastLetter = character;
            }
        }
        return leastLetter;
    }

    private String withoutLastCharacter(String string){
        int indexOfLastButOneCharacter = string.length() - 2;
        return string.substring(0,indexOfLastButOneCharacter);
    }
}
