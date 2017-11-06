import static java.lang.Math.sqrt;

/**
 * Created by Michal on 24.05.2017.
 */
public class Quadratic {

    private double a;
    private double b;
    private double c;

    public static final int INFINITE_RESULTS = Integer.MAX_VALUE;

    /**
     * Quadratic equation has the form of
     * a*x^2 + bx + c = 0
     */
    public Quadratic(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Calculater the number of real solutions.
     * @return INFINITE_RESULTS if equation is Identity, number (0, 1 or 2) in all other cases.
     */
    public int countResults() {
        return -1;
    }

    /**
     * @return -
     *      array of real solutions or null if there is no real solution
     *      if equation is contradictory it will return one element double array with Double.NEGATIVE_INFINITY;
     *      if equation is identity it will return one element double array with Double.POSITIVE_INFINITY;
     */
    public double[] solve() {
        return null;
    }

    @Override
    public String toString() {
        double[] results = this.solve();
        StringBuffer quad = new StringBuffer("Quadratic equation " + this.a + "*x^2 + " + this.b + "*x + " + this.c + " = 0");
        return quad.toString();
    }

}
