public class BipolarActivationFunction implements I_ActivationFunction {
    @Override
    public double activate(Double sum) {
        return (1 - (Math.pow(Math.E, - sum))) / (1 + (Math.pow(Math.E, -sum)));
    }
}
