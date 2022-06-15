import com.google.gson.Gson;
import java.io.IOException;


public class NeuralNetwork {
    private double[][] inputs;
    private double[][] outputs;
    private int[] nodesInLayers;
    private String activationFunction;
    private Neuron[][] neuronLayers;
    private double learningRate;
    private int iterations;

    public NeuralNetwork(double[][] inputs, double[][] outputs,int[] nodesInLayers, String activationFunction) {
        this.activationFunction = activationFunction;
        this.inputs = inputs;
        this.outputs = outputs;
        this.nodesInLayers = nodesInLayers;
        this.learningRate = 0.5;
        this.iterations = 50000;
        createLayers();
        randomizeNeuronValues();
    }

    public void createLayers() {
        int numOfNeuronLayers = nodesInLayers.length - 1;
        neuronLayers = new Neuron[numOfNeuronLayers][];
        double [] initInputs = new double[nodesInLayers[0]];

        Neuron[] layer;

        for (int i = 1; i < nodesInLayers.length; i++) {
            layer = new Neuron[nodesInLayers[i]];
            for (int j = 0; j < nodesInLayers[i]; j++) {
                if (i == 1) {
                    layer[j] = new Neuron(initInputs);
                } else {
                    int neuronsInPreviousLayer = neuronLayers[i - 2].length;
                    double[] tempInputs = new double[neuronsInPreviousLayer];
                    for (int k = 0; k < neuronsInPreviousLayer; k++) {
                        tempInputs[k] = 0;
                    }
                    layer[j] = new Neuron(tempInputs);
                }
            }
            this.neuronLayers[i - 1] = layer;
        }
    }

    public void randomizeNeuronValues(){
        for (Neuron[] layer : neuronLayers){
            for (Neuron neuron : layer){
                neuron.initializeValues();
            }
        }
    }

    public void calcError(double[] outputs) {
        System.out.println(outputs[0]);
        int lastLayerIndex = neuronLayers.length - 1;
        for (int i = lastLayerIndex; i >= 0; i--) {
            if (i == lastLayerIndex) {
                int indexLast = 0;
                for (Neuron n : neuronLayers[lastLayerIndex]) {
                    n.setError((1 - n.getOutput()) * n.getOutput());
                    n.setDelta((outputs[indexLast] - n.getOutput()) * n.getError());
                    indexLast++;
                }
            } else {
                for (Neuron n : neuronLayers[i]) {
                    n.setError((1 - n.getOutput()) * n.getOutput());
                }
                int index = 0;
                for (Neuron currentNeuron : neuronLayers[i]){
                    currentNeuron.setDelta(0);
                    for (Neuron nextNeuron : neuronLayers[i + 1]){
                        currentNeuron.setDelta(currentNeuron.getDelta() + (currentNeuron.getError() * nextNeuron.getDelta() * nextNeuron.getWeights()[index].getValue()));
                    }
                    index++;
                }
            }
        }
    }

    public void updateWeights() {
        for (int i = neuronLayers.length - 1; i >= 0; i--) {
            for (int j = 0; j < neuronLayers[i].length; j++) {
                for (int k = 0; k < neuronLayers[i][j].getWeights().length; k++) {
                    neuronLayers[i][j].getWeights()[k].setValue(
                            neuronLayers[i][j].getWeights()[k].getValue() + (neuronLayers[i][j].getDelta() * neuronLayers[i][j].getInputs()[k] * learningRate));
                }
                neuronLayers[i][j].setBias(
                        neuronLayers[i][j].getBias() + (neuronLayers[i][j].getDelta() * learningRate));
            }
        }
    }

    public void forward(double[] inputs) {
        double[] inp = new double[]{};
        double[] tempInp;

        for (int i = 0; i < neuronLayers.length; i++) {
            tempInp = new double[neuronLayers[i].length];

            for (int j = 0; j < neuronLayers[i].length; j++) {
                neuronLayers[i][j].setInputs((i != 0) ? inp : inputs);
                neuronLayers[i][j].calcSum();
                neuronLayers[i][j].activate(ActivationFunctionFactory.getActivationFunction(this.activationFunction));
                tempInp[j] = neuronLayers[i][j].getOutput();
            }
            inp = tempInp;
        }
    }

    public void train(){
        int inputsSize = inputs.length;
        int index = 0;

        for (int i = 0; i < iterations; i++){
            forward(inputs[index]);
            calcError(outputs[index]);
            updateWeights();
            index++;
            index = (index == inputsSize) ? 0 : index;
        }
    }

    public double[] guess(double[] inputs, String weightsPath){
        String weights = Utils.readFile(weightsPath);
        Gson gson = new Gson();
        NeuralNetwork nn = gson.fromJson(weights, NeuralNetwork.class);
        int lastLayer = nn.nodesInLayers.length - 1;

        for (Neuron neuron : nn.getNeuronLayers()[0]){
            neuron.setInputs(inputs);
        }
        nn.forward(inputs);

        double[] results = new double[nodesInLayers[lastLayer]];

        int index = 0;

        for (Neuron neuron : nn.getNeuronLayers()[lastLayer - 1]){
            results[index] = neuron.getOutput();
            index++;
        }

        return results;
    }

    public void saveWeights(){
        Gson gson = new Gson();


        try {
            Utils.saveToFile("trained network.txt", gson.toJson(this));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public double[][] getInputs() {
        return inputs;
    }

    public void setInputs(double[][] inputs) {
        this.inputs = inputs;
    }

    public double[][] getOutputs() {
        return outputs;
    }

    public void setOutputs(double[][] outputs) {
        this.outputs = outputs;
    }

    public int[] getNetworkSize() {
        return nodesInLayers;
    }

    public void setNetworkSize(int[] networkSize) {
        this.nodesInLayers = networkSize;
    }

    public String getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(String activationFunction) {
        this.activationFunction = activationFunction;
    }

    public Neuron[][] getNeuronLayers() {
        return neuronLayers;
    }

    public void setNeuronLayers(Neuron[][] neuronLayers) {
        this.neuronLayers = neuronLayers;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
