package JernexMachineSystem.JernexApps.JeykanyAI.JeykanyBrain;

//----EM BREVE!------------------------------

import java.util.HashMap;

public class Neuron {
    private HashMap<String, Integer> weights = new HashMap<>();
    private int WeightLimit = 0;

    public void WeightDelimiter(int limit) {
        WeightLimit = limit;
    }
    public void Weights(String string, int weight) {
        weights.put(string, weight);
    }
    public int ActivationFunction(HashMap<String, Integer> input) {
        int total = 0;

        for (String string: input.keySet()) {
            int stringWeight = input.get(string);

            if (weights.containsKey(string)) {
                int weight = weights.get(string);
                total += (stringWeight * weight);
            }
        }
        if (total >= WeightLimit) {
            return 1;
        } else {
            return 0;
        }
    }
}
