
public class NeuralNetwork {
    private double[] inputs;
    private double[] outputs;
    private int[] networkSize;
    private ActivationFunction activationFunction;
    private Neuron[][] neuronLayers;
    private double learningRate;

    public NeuralNetwork(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public void createLayers() {
        neuronLayers = new Neuron[networkSize.length][];
        Neuron[] layer;

        for (int i = 0; i < networkSize.length; i++) {
            layer = new Neuron[networkSize[i]];
            for (int j = 0; j < networkSize[i]; j++) {
                if (i == 0) {
                    Neuron neuron = new Neuron(inputs);
                    layer[j] = neuron;
                } else {
                    int neuronsInPreviousLayer = neuronLayers[i - 1].length;
                    double[] tempInputs = new double[neuronsInPreviousLayer];
                    for (int k = 0; k < neuronsInPreviousLayer; k++) {
                        tempInputs[k] = 0;
                    }
                    Neuron neuron = new Neuron(tempInputs);
                    layer[j] = neuron;
                }
            }
            this.neuronLayers[i] = layer;
        }
    }

    public void calcError() {
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

    public void forward() {
        double[] inp = new double[]{};
        double[] tempInp;

        for (int i = 0; i < neuronLayers.length; i++) {
            tempInp = new double[neuronLayers[i].length];

            for (int j = 0; j < neuronLayers[i].length; j++) {
                neuronLayers[i][j].setInputs((i != 0) ? inp : this.inputs);
                neuronLayers[i][j].calcSum();
                neuronLayers[i][j].activate(this.activationFunction);
                tempInp[j] = neuronLayers[i][j].getOutput();
            }
            inp = tempInp;
        }
    }


    public double[] getInputs() {
        return inputs;
    }

    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    public double[] getOutputs() {
        return outputs;
    }

    public void setOutputs(double[] outputs) {
        this.outputs = outputs;
    }

    public int[] getNetworkSize() {
        return networkSize;
    }

    public void setNetworkSize(int[] networkSize) {
        this.networkSize = networkSize;
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
}
