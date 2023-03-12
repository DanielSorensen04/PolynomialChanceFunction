import java.util.List;

public class PolynomialFunctionElement {
    double coefficient = 1;
    double exponent = 1;

    public PolynomialFunctionElement(double coefficient, double exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double evaluate(double x) {
        return coefficient * Math.pow(x, exponent);
    }

}

