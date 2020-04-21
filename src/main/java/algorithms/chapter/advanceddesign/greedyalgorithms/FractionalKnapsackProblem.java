package algorithms.chapter.advanceddesign.greedyalgorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FractionalKnapsackProblem {

    public int fractionalKnapsackGreedy(int[] itemValues, int[] itemWeight, int sackWeight) {
        int n = itemValues.length;
        FractionalKnapsackItem[] knapsackItems = new FractionalKnapsackItem[n];
        for (int i = 0; i < n; i++) {
            knapsackItems[i] = new FractionalKnapsackItem(itemValues[i],
                                            itemWeight[i],
                                    itemValues[i] / itemWeight[i]);
        }
        Arrays.sort(knapsackItems, Collections.reverseOrder(Comparator.comparingInt(FractionalKnapsackItem::getFraction)));

        int maxValue = 0;
        int index = 0;
        while (knapsackItems[index].getItemWeight() < sackWeight && index < n) {
            maxValue = maxValue + knapsackItems[index].getItemValue();
            sackWeight = sackWeight - knapsackItems[index].getItemWeight();
            index++;
        }
        if(index < n) {
            maxValue = maxValue + sackWeight * knapsackItems[index].getFraction();
        }

        return maxValue;
    }

}
