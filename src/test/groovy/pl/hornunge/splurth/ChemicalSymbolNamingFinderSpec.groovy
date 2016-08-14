package pl.hornunge.splurth

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class ChemicalSymbolNamingFinderSpec extends Specification {

    @Subject
    ChemicalSymbolNamingFinder chemicalSymbolNamingFinder = new ChemicalSymbolNamingFinder()

    @Unroll
    def 'should find first valid symbol in alphabetical order; name = [#name], symbol = [#symbol]'() {
        expect:
            chemicalSymbolNamingFinder.findFirstValidSymbolFor(name) == symbol
        where:
            name        || symbol
            "Gozerium"  || "Ei"
            "Slimyrine" || "Ie"
    }

    def 'should throw exception when name is null'(){
        when:
            chemicalSymbolNamingFinder.findFirstValidSymbolFor(null)
        then:
            Throwable t = thrown()
            t.class == NullPointerException
            t.getMessage().contains("Name mustn't be null.")
    }

    def 'should throw exception when name is of length < 2'(){
        when:
            chemicalSymbolNamingFinder.findFirstValidSymbolFor(name)
        then:
            Throwable t = thrown()
            t.class == IllegalArgumentException
            t.getMessage().contains("Name must have at least 2 characters.")
        where:
            name << ["Z", ""]
    }
}
