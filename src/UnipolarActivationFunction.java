public class UnipolarActivationFunction implements ActivationFunction{
    @Override
    public double activate(Double sum) {
            return 1 / (1 + (Math.pow(Math.E, -sum)));
    }
}
