package pl.hornunge.splurth;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

class ChemicalSymbolNamingCounter {

    public int countDistinctValidSymbolsFor(String name) {
        checkNotNull(name, "Name mustn't be null.");

        name = name.toLowerCase();
        Set<String> validSymbols = new HashSet<>();
        for (int firstLetterIndex = 0; firstLetterIndex < name.length() - 1; firstLetterIndex++){
            for (int secondLetterIndex = firstLetterIndex + 1; secondLetterIndex < name.length(); secondLetterIndex++){
                validSymbols.add(createSymbol(name, firstLetterIndex, secondLetterIndex));
            }
        }
        return validSymbols.size();
    }

    private String createSymbol(String name, int firstLetterIndex, int secondLetterIndex) {
        return "" + Character.toUpperCase(name.charAt(firstLetterIndex)) + name.charAt(secondLetterIndex);
    }
}
