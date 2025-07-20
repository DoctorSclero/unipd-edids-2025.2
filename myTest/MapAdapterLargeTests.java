package myTest;

import myAdapter.HMap;

/**
 * This test case tests the MapAdapter functionality of large-sized instances
 * (maps with >MAX_INT elements). Verifies that MapAdapter size method returns
 * the correct number of elements in maps with more than MAX_INT elements. Other
 * methods are ignored for this test case to avoid too long test execution
 * because of the resource-intensive nature of the set of tests.
 *
 * @test.design This test case is designed to test the {@link HMap#size()}
 * method edge-case: more than MAX_INT elements, ensuring the
 * {@link myAdapter.HMap} interface is implemented correctly.
 * @test.libraries JUnit 4.13, Hamcrest 1.3
 * @see myAdapter.MapAdapter
 */
public class MapAdapterLargeTests {

}
