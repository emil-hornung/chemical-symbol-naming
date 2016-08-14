package pl.hornunge.splurth

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@Unroll
class ChemicalSymbolNamingValidatorSpec extends Specification {

    public static final String SOME_NAME = 'somename'
    public static final String NULL_SYMBOL = null

    @Subject
    ChemicalSymbolNamingValidator chemicalSymbolNamingValidator = new ChemicalSymbolNamingValidator();


    def 'should throw exception when symbol length is not 2, symbol = [#symbol]'() {
        when:
            chemicalSymbolNamingValidator.symbolFollowsNamingRules(SOME_NAME, symbol)
        then:
            Throwable e = thrown()
            e.class == IllegalArgumentException
            e.getMessage().contains("The length of symbol must be 2.")
        where:
            symbol << ["", "a", "abc", "zxcasdqwe"]
    }

    def 'should throw exception when symbol is null'() {
        when:
            chemicalSymbolNamingValidator.symbolFollowsNamingRules(SOME_NAME, NULL_SYMBOL)
        then:
            Throwable e = thrown()
            e.class == NullPointerException
            e.getMessage().contains("Symbol mustn't be null.")
    }


    def 'should return false when not both letters of symbol appear in name; name = [#name], symbol = [#symbol]'() {
        expect:
            !chemicalSymbolNamingValidator.symbolFollowsNamingRules(name, symbol)
        where:
            name = "Mercury"
            symbol << ["Hg", "Mx", "Xm"]

    }

    def 'should return true when both letters of symbol appear in name; symbol = [#symbol]'() {
        expect:
            chemicalSymbolNamingValidator.symbolFollowsNamingRules('Mercury', symbol)
        where:
            symbol << ["Cy", "Me", "My", "Er"]
    }

    def 'should return false when both letters of symbol appear in name but not in the same order; symbol = [#symbol]'() {
        expect:
            !chemicalSymbolNamingValidator.symbolFollowsNamingRules("Silver", symbol)
        where:
            symbol << ["Is", "Li", "Rs", "Rv", "Vi"]
    }

    def 'should return true when both letters of symbol appear in name and in the same order; symbol = [#symbol]'() {
        expect:
            chemicalSymbolNamingValidator.symbolFollowsNamingRules("Silver", symbol)
        where:
            symbol << ["Si", "Il", "Sr", "Vr", "Iv"]
    }

    def 'should return true when both letters of symbol appear in name and order does not matter; symbol = [#symbol]'() {
        expect:
            chemicalSymbolNamingValidator.symbolFollowsNamingRules("Magnesium", symbol)
        where:
            symbol << ["Ma", "Am"]
    }

    def 'should return false when both letters of symbol are the same but name contains the letter only once; symbol = [#symbol]'() {
        expect:
            !chemicalSymbolNamingValidator.symbolFollowsNamingRules("Xenon", symbol)
        where:
            symbol << ["Xx", "Ee", "Oo"]
    }

    def 'should return true when both letters of symbol are the same and name contains the letter twice'() {
        expect:
            chemicalSymbolNamingValidator.symbolFollowsNamingRules("Xenon", "Nn")
    }

    def 'should thrown an exception when name or symbol contains other characters than a-z; name = [#name], symbol = [#symbol]'() {
        when:
            chemicalSymbolNamingValidator.symbolFollowsNamingRules(name, symbol)
        then:
            Throwable t = thrown()
            t.class == IllegalArgumentException
            t.getMessage().contains("must only contains letter a to z")
        where:
            name       | symbol
            "Me1rcury" | "Me"
            "Me2rcury" | "M1"
            "Mercury"  | "M2"
            "Meąrcury" | "Me"
    }

    @SuppressWarnings("GroovyPointlessBoolean")
    def '#name -> #symbol should return #result'() {
        expect:
            chemicalSymbolNamingValidator.symbolFollowsNamingRules(name, symbol)  == result
        where:
            name          | symbol || result
            "Spenglerium" | "Ee"   || true
            "Zeddemorium" | "Zr"   || true
            "Venkmine"    | "Kn"   || true
            "Stantzon"    | "Zt"   || false
            "Melintzum"   | "Nn"   || false
            "Tullium"     | "Ty"   || false
    }

}
