import java.util.*;

public class RobberyReward {

    private double totalValue;
    private double totalNotes;
    private List<Double> rewards = new ArrayList<Double>();

    private List<PolynomialFunctionElement> totalValueFunctionElements = Arrays.asList(
            new PolynomialFunctionElement(0.415, 2),
            new PolynomialFunctionElement(-25, 1),
            new PolynomialFunctionElement(425, 0));

    public RobberyReward(int derivativeSize) {
        PolynomialFunction totalValueFunction = new PolynomialFunction(totalValueFunctionElements);
        PolynomialChanceFunction totalValueChanceFunction = new PolynomialChanceFunction(16, 31, 122, 40, totalValueFunction, derivativeSize);
        this.totalValue = totalValueChanceFunction.getRandomPoint().getNearestValue()*10000.0;

        Random generator = new Random();
        this.totalNotes = 10 + generator.nextInt(6);

        double bigRewards = Math.round(totalNotes/3.0);
        double midRewards = Math.round((totalNotes - bigRewards)/2.0);
        double smallRewards = totalNotes - bigRewards - midRewards;

        HashMap<Float, Double> percentageAndAmount = new HashMap<Float, Double>();
        percentageAndAmount.put(0.50f, bigRewards);
        percentageAndAmount.put(0.35f, midRewards);
        percentageAndAmount.put(0.15f, smallRewards);

        int index = 0;
        for(Map.Entry<Float, Double> entry : percentageAndAmount.entrySet()) {
            index++;
            for (int i = 1; i<=entry.getValue(); i++) {
                double percentage = entry.getKey()/entry.getValue();
                Random random = new Random();
                double reward = totalValue * (random.nextInt((int) (percentage  + 0.035)) - percentage - 0.035);
                if (reward > 200) {
                    rewards.add(reward);
                }
            }
        }
    }

    public List<Double> getRewards() {
        return rewards;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double getTotalNotes() { return totalNotes; }
}
