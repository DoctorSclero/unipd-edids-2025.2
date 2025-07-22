// Giacomo Simonetto - 2109923 - Secondo appello 2025 di Elementi di Ingegneria del Software

/**
 * Pacchetto contenente le classi di test per tutti i metodi della classe {@link myAdapter.MapAdapter} con le relative
 * viste e gli iteratori associati.
 *
 * <p>
 * Sono contenute le seguenti classi con i test dei singoli metodi:
 * <ul>
 *   <li> {@link myTestNew.TestEmptyMapAdapter}: testa i metodi della classe {@link myAdapter.MapAdapter} su una mappa vuota;</li>
 *   <li> {@link myTestNew.TestEmptyKeySet}: testa i metodi della vista {@link myAdapter.MapAdapter.KeySetAdapter} di una mappa vuota;</li>
 *   <li> {@link myTestNew.TestEmptyValuesCollection}: testa i metodi della vista {@link myAdapter.MapAdapter.ValuesCollectionAdapter} di una mappa vuota;</li>
 *   <li> {@link myTestNew.TestEmptyEntrySet}: testa i metodi della vista {@link myAdapter.MapAdapter.EntrySetAdapter} di una mappa vuota;</li>
 *   <li> {@link myTestNew.TestPopulatedMapAdapter}: testa i metodi della classe {@link myAdapter.MapAdapter} su una mappa popolata;</li>
 *   <li> {@link myTestNew.TestPopulatedKeySet}: testa i metodi della vista {@link myAdapter.MapAdapter.KeySetAdapter} di una mappa popolata;</li>
 *   <li> {@link myTestNew.TestPopulatedValuesCollection}: testa i metodi della vista {@link myAdapter.MapAdapter.ValuesCollectionAdapter} di una mappa popolata;</li>
 *   <li> {@link myTestNew.TestPopulatedEntrySet}: testa i metodi della vista {@link myAdapter.MapAdapter.EntrySetAdapter} di una mappa popolata;</li>
 *   <li> {@link myTestNew.TestEntryAdapter}: testa i metodi della classe {@link myAdapter.MapAdapter.EntryAdapter}.</li>
 * </ul>
 *
 * <p>
 * Sono inoltre contenute le seguenti classi per permettere l'esecuzione delle classi di test:
 * <ul>
 *   <li> la classe {@link myTestNew.AllTestsSuite} che raggruppa tutti i test in un'unica suite di test;</li>
 *   <li> la classe {@link myTestNew.TestRunner} che esegue i test raggruppati nella suite {@link myTestNew.AllTestsSuite}.</li>
 * </ul>
 *
 * <p>
 * È stata inoltre implementata la classe {@link myTestNew.SimpleHMapWithNulls} che implementa l'interfaccia {@link myAdapter.HMap}
 * e supporta valori {@code null} come chiavi o valori delle entry della mappa. Questa classe è richiesta per testare
 * alcuni metodi particolari (ad esempio {@link myAdapter.MapAdapter#putAll(HMap m)}) per verificare il corretto lancio
 * delle eccezioni nella gestione dei valori {@code null}. Viene utilizzata, inoltre, per agevolare la creazione di
 * entry senza dover ricorrere a magheggi strani (siccome {@link myAdapter.MapAdapter.EntryAdapter} ha il costruttore
 * non accessibile). Siccome il suo impiego è molto limitato, tutti i metodi non necessari non sono implementati e
 * lanciano {@link myAdapter.UnsupportedOperationException} se invocati.
 */
package myTestNew;
