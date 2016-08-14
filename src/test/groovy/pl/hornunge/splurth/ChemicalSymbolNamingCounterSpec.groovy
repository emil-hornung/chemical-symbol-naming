package pl.hornunge.splurth

import spock.lang.Specification
import spock.lang.Subject

class ChemicalSymbolNamingCounterSpec extends Specification{
    @Subject
    ChemicalSymbolNamingCounter chemicalSymbolNamingCounter = new ChemicalSymbolNamingCounter()

    def 'should return 11 for Zuulon'() {
        expect:
            chemicalSymbolNamingCounter.countDistinctValidSymbolsFor('Zuulon') == 11
    }

    def 'should return 0 for name of length < 2'() {
        expect:
            chemicalSymbolNamingCounter.countDistinctValidSymbolsFor(name) == 0
        where:
            name << ["Z", ""]
    }

    def 'should throw exception when name is null'(){
        when:
            chemicalSymbolNamingCounter.countDistinctValidSymbolsFor(null)
        then:
            Throwable t = thrown()
            t.class == NullPointerException
            t.getMessage().contains("Name mustn't be null.")
    }
}
