public class Neuron {
    private double[] inputs;
    private Weight[] weights;
    private double bias;
    private double output;
    private double error;
    private double delta;
    private double sum;

    public Neuron(double[] inputs){
        this.inputs = inputs;
        initializeValues();
    }

    public void initializeValues(){
        randomizeBias();
        randomizeWeights();
    }

    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    public void activate(ActivationFunction activationFunction){
        this.output = activationFunction.activate(sum);
    }

    public void randomizeBias(){
        this.bias = Math.random() * 2 - 1;
    }

    public void randomizeWeights(){
        weights = new Weight[inputs.length];
        for (int i = 0; i < inputs.length; i++){
            weights[i] = new Weight( (Math.random() * 2 )-1);
        }
    }

    public void calcSum(){
        double tempSum = 0;

        for (int i = 0; i < this.inputs.length; i++){
            tempSum += this.inputs[i] * this.weights[i].getValue();
        }
        this.sum = tempSum;
        this.sum += this.bias;
    }

    public double[] getInputs() {
        return inputs;
    }

    public Weight[] getWeights() {
        return weights;
    }

    public void setWeights(Weight[] weights) {
        this.weights = weights;
    }

    public double getOutput() {
        return output;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getSum() {
        return sum;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

}
