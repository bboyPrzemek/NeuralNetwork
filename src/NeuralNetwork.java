
public class NeuralNetwork {
    private double[][] inputs;
    private double[][] outputs;
    private int[] nodesInLayers;
    private ActivationFunction activationFunction;
    private Neuron[][] neuronLayers;
    private double learningRate;
    private int iterations;

    public NeuralNetwork(double[][] inputs, double[][] outputs,int[] nodesInLayers, ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        this.inputs = inputs;
        this.outputs = outputs;
        this.nodesInLayers = nodesInLayers;
        this.learningRate = 0.5;
        this.iterations = 50000;
        createLayers();
    }

    public void createLayers() {
        int numOfNeuronLayers = nodesInLayers.length - 1;
        neuronLayers = new Neuron[numOfNeuronLayers][];
      //  System.out.println(neuronLayers.length);
        Neuron[] layer;

        for (int i = 1; i < nodesInLayers.length; i++) {
            layer = new Neuron[nodesInLayers[i]];
            for (int j = 0; j < nodesInLayers[i]; j++) {
                if (i == 1) {
                    Neuron neuron = new Neuron(inputs[0]);
                    layer[j] = neuron;
                } else {
                    System.out.println(i);
                    int neuronsInPreviousLayer = neuronLayers[i - 2].length;
                    double[] tempInputs = new double[neuronsInPreviousLayer];
                    for (int k = 0; k < neuronsInPreviousLayer; k++) {
                        tempInputs[k] = 0;
                    }
                    Neuron neuron = new Neuron(tempInputs);
                    layer[j] = neuron;
                }
            }
            this.neuronLayers[i - 1] = layer;
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
                neuronLayers[i][j].activate(this.activationFunction);
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

        //save weights to file
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

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public void setActivationFunction(ActivationFunction activationFunction) {
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
