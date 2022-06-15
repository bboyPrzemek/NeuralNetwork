public class ActivationFunctionFactory {

    public static I_ActivationFunction getActivationFunction(String name){
        I_ActivationFunction activationFunction;

        switch(name){
            case "unipolar":
                activationFunction = new UnipolarActivationFunction();
                break;

            case "bipolar" :
                activationFunction = new BipolarActivationFunction();

                default :
                    activationFunction = new UnipolarActivationFunction();

        }

return activationFunction;
    }
}
