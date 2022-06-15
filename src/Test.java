import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


public class Test {
    public static void main(String[] args) {


        double[][] inputs = new double[][]{
                {1,1,1,1,0,1,1,0,1,1,0,1,1,1,1},
                {0,0,1,0,1,1,1,0,1,0,0,1,0,0,1},
                {1,1,1,1,0,1,0,1,0,1,0,0,1,1,1},
                {1,1,1,0,0,1,1,1,1,0,0,1,1,1,1},
                {1,0,1,1,0,1,1,1,1,0,0,1,0,0,1},
                {1,1,1,1,1,0,1,1,1,0,0,1,1,1,1},
                {1,1,1,1,0,0,1,1,1,1,0,1,1,1,1},
                {1,1,1,0,0,1,0,0,1,0,0,1,0,0,1},
                {1,1,1,1,0,1,1,1,1,1,0,1,1,1,1},
                {1,1,1,1,0,1,1,1,1,0,0,1,1,1,1}
        };

        double[][] outputs = new double[][]{
                {1,0,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,0,0,1}
        };
        int[] nodes = new int[]{15,15,10};


        NeuralNetwork net = new NeuralNetwork(inputs, outputs,nodes, "unipolar");
//        net.setIterations(50000);
//        net.train();
//        net.saveWeights();

        for (double d : net.guess(new double[]{1,0,1,1,0,1,1,0,1,1,0,1,1,1,1}, "trained network.txt")){
            System.out.println("output" + d);
        }
//
//
//        List<Double> w = new ArrayList<>();
//        List<Double> b = new ArrayList<>();
//
//        double[] ww = new double[6];
//        double[] bb = new double[3];
//        for (int i = 0; i < net.getNeuronLayers().length; i++){
//        for (int j = 0; j < net.getNeuronLayers()[i].length; j++){
//
//           for (int m = 0; m < net.getNeuronLayers()[i][j].getWeights().length; m++){
//               w.add(net.getNeuronLayers()[i][j].getWeights()[m].getValue());
//           }
//           b.add(net.getNeuronLayers()[i][j].getBias());
//
//        }
//    }
//       for (int i = 0; i < w.size(); i++){
//           ww[i] = w.get(i);
//       }
//        for (int i = 0; i < b.size(); i++){
//            bb[i] = b.get(i);
//        }
//        System.out.println(ww[0]);
//        System.out.println(ww[1]);
//        System.out.println(bb[0]);
//
//        System.out.println(ww[2]);
//        System.out.println(ww[3]);
//        System.out.println(bb[1]);
//
//        System.out.println(ww[4]);
//        System.out.println(ww[5]);
//        System.out.println(bb[2]);
//
//
//        double sum1 = (ww[0] * 0) + ((0 * ww[1]) + bb[0]);
//        double output1 =  1 / (1 + (Math.pow(Math.E, - sum1)));
//
//        double sum2 = (ww[2] * 0) + ((0 * ww[3]) + bb[1]);
//        double output2 = 1 / (1 + (Math.pow(Math.E, - sum2)));
//
//        double sum3 = (ww[4] * output1) + ((output2 * ww[5]) + bb[2]);
//        double output3 = 1 / (1 + (Math.pow(Math.E, - sum3)));
//
//        System.out.println("0 0   " + output3);
//
//        double sum12 = (ww[0] * 0) + ((1 * ww[1]) + bb[0]);
//        double output12 =  1 / (1 + (Math.pow(Math.E, - sum12)));
//
//        double sum22 = (ww[2] * 0) + ((1 * ww[3]) + bb[1]);
//        double output22 = 1 / (1 + (Math.pow(Math.E, - sum22)));
//
//        double sum32 = (ww[4] * output12) + ((output22 * ww[5]) + bb[2]);
//        double output32 = 1 / (1 + (Math.pow(Math.E, - sum32)));
//
//        System.out.println("0 1   " + output32);
//
//        double sum13 = (ww[0] * 1) + ((0 * ww[1]) + bb[0]);
//        double output13 =  1 / (1 + (Math.pow(Math.E, - sum13)));
//
//        double sum23 = (ww[2] * 1) + ((0 * ww[3]) + bb[1]);
//        double output23 = 1 / (1 + (Math.pow(Math.E, - sum23)));
//
//        double sum33 = (ww[4] * output13) + ((output23 * ww[5]) + bb[2]);
//        double output33 = 1 / (1 + (Math.pow(Math.E, - sum33)));
//
//        System.out.println("1 0   " + output33);
//
//        double sum14 = (ww[0] * 1) + ((1 * ww[1]) + bb[0]);
//        double output14 =  1 / (1 + (Math.pow(Math.E, - sum14)));
//
//        double sum24 = (ww[2] * 1) + ((1 * ww[3]) + bb[1]);
//        double output24 = 1 / (1 + (Math.pow(Math.E, - sum24)));
//
//        double sum34 = (ww[4] * output14) + ((output24 * ww[5]) + bb[2]);
//        double output34 = 1 / (1 + (Math.pow(Math.E, - sum34)));
//
//        System.out.println("1 1    " + output34);
//



    }
}
