import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class PolynomialChanceFunction {

    public static double simplify(double i) {
        return Math.floor(i*100)/100;
    }

    private DuplicatedValueMap<Double, Double> functionPointMap = new DuplicatedValueMap<Double, Double>();
    public static Entry<Double, Double> randomPoint;
    private double startX;
    private double endX;
    private double startY;
    private double endY;
    private PolynomialFunction function;

    public PolynomialChanceFunction(double startX, double endX, double startY, double endY, PolynomialFunction function, double derivativeSize) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.function = function;

        double deltaX = endX - startX;
        double deltaY = Math.max(endY, startY) - Math.min(endY, startY);

        double deriY = deltaY/derivativeSize;
        double deriX = deltaX/derivativeSize;

        for (double x = startX; x<endX; x+=deriX) {
            double valueY = simplify(function.evaluate(x));
            for (double y = Math.min(endY, startY); y<valueY; y+=deriY) {
                functionPointMap.put(simplify(x), simplify(y));
            }
        }
    }

    public double getStartX() {
        return startX;
    }

    public double getEndX() {
        return endX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndY() {
        return startY;
    }

    public PolynomialFunction getFunction() {
        return function;
    }

    public DuplicatedValueMap<Double, Double> getPointMap() {
        return functionPointMap;
    }

    @SuppressWarnings("unchecked")
    public PolynomialChanceFunction getRandomPoint() {
        Random generator = new Random();
        int index = 0;
        int endIndex = generator.nextInt((int) functionPointMap.size());
        for (Map.Entry<Double, List<Double>> entry : functionPointMap.entrySet()) {
            for (Double value : entry.getValue()) {
                index++;
                if (index == endIndex) this.randomPoint = new AbstractMap.SimpleEntry<Double, Double>(entry.getKey(), value);
            }
        }
        return this;
    }

    public double getNearestValue() { // x = key, y = value
        double valueX = randomPoint.getKey();
        return NearestAxisValue.findClosest((int[]) NearestAxisValue.getNumbersBetween(Math.min(endX, startX), Math.max(endY, startY)), (int) valueX);
    }
}