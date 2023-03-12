import java.util.List;

public class PolynomialFunction {
    List<PolynomialFunctionElement> elements;

    public PolynomialFunction(List<PolynomialFunctionElement> elements) {
        this.elements = elements;
    }

    public double evaluate(double x) {
        double evaluation = 0;
        for (PolynomialFunctionElement element : elements) {
            evaluation += element.evaluate(x);
        }
        return evaluation;
    }

    public List<PolynomialFunctionElement> getElements() {
        return elements;
    }
}
