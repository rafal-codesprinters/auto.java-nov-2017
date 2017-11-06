import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Michal on 24.05.2017.
 */
public class QuadraticTest {

    private static final double PRECISION = 0.000001;

    private void assertHasTwoResults(Quadratic q) {
        assertTrue ( q.countResults() == 2);
    }

    private void assertHasOneResult(Quadratic q) {
        assertTrue (q.countResults() == 1);
    }

    private void assertHasNoResults(Quadratic q) {
        assertTrue (q.countResults() == 0);
    }

    private void assertHasInfiniteResults(Quadratic q) {
        assertTrue( q.countResults() == Quadratic.INFINITE_RESULTS);
    }

    private void assertIsContradictory(Quadratic q) {
        double[] res = q.solve();
        assertNotNull(res);
        assertTrue(res.length == 1);
        assertTrue(res[0] == Double.NEGATIVE_INFINITY);
    }

    private void assertIsIdentity(Quadratic q) {
        double[] res = q.solve();
        assertNotNull(res);
        assertTrue(res.length == 1);
        assertTrue(res[0] == Double.POSITIVE_INFINITY);
    }

    private void assertResults(Quadratic q, double ... results) {

        if (q.countResults() != Quadratic.INFINITE_RESULTS ) {
            assertTrue(q.toString() + " has a different number of results", q.countResults() == results.length);
            double[] res = q.solve();
            Arrays.sort(res);
            Arrays.sort(results);
            assertArrayEquals(res, results, PRECISION);
        }
    }

    private Quadratic quadratic2real = new Quadratic(1, 0, -4);
    private Quadratic quadratic1real = new Quadratic(9, 12, 4);
    private Quadratic quadratic0real = new Quadratic(2, 4, 25);
    private Quadratic quadraticContradictory = new Quadratic(0, 0, 1);
    private Quadratic quadraticIdentity = new Quadratic(0, 0, 0);

    @org.junit.Test
    public void testIfThereAreTwoSolutions() throws Exception {
        assertHasTwoResults(quadratic2real);
        assertResults(quadratic2real, -2.0, 2.0);
    }

    @org.junit.Test
    public void testIfThereIsOneSolutions() throws Exception {
        assertHasOneResult(quadratic1real);
        assertResults(quadratic1real, -0.66666666);
    }

    @org.junit.Test
    public void testIfThereAreNoSolutions() throws Exception {
        assertHasNoResults(quadratic0real);
    }

    @org.junit.Test
    public void testIfContradictoryHasNoSolution() throws Exception {
        assertHasNoResults(quadraticContradictory);
        assertIsContradictory(quadraticContradictory);
    }

    @org.junit.Test
    public void testIfIdentityHasInfiniteNumberOfSolution() throws Exception {
        assertHasInfiniteResults(quadraticIdentity);
        assertIsIdentity(quadraticIdentity);
        assertResults(quadraticIdentity,
                -1000.0,
                0.0,
                256.0,
                9686867564748595948.0,
                0.0000000000000000000000000000000001);
    }


}