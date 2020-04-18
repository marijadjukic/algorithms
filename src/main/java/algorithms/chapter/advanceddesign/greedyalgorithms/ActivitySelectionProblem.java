package algorithms.chapter.advanceddesign.greedyalgorithms;

import java.util.HashSet;
import java.util.Set;

public class ActivitySelectionProblem {

    public Set<Integer> activitySelectionRecursive(int[] start, int[] finish) {
        Set<Integer> compatibleActivities = new HashSet<>();
        compatibleActivities.add(0);
        activitySelectionRecursive(start, finish, 0, start.length, compatibleActivities);
        return compatibleActivities;
    }

    private void activitySelectionRecursive(int[] start, int[] finish, int k, int n, Set<Integer> compatibleActivities) {
        int m = k + 1;

        while (m < n && start[m] < finish[k]) {
            m++;
        }
        if (m < n) {
            compatibleActivities.add(m);
            activitySelectionRecursive(start, finish, m, n, compatibleActivities);
        }
    }

    public Set<Integer> activitySelectionGreedy(int[] start, int[] finish) {
        Set<Integer> compatibleActivities = new HashSet<>();
        compatibleActivities.add(0);

        int currentActivity = 0;
        for (int i = 1; i < start.length; i++) {
            if(start[i] >= finish[currentActivity]) {
                compatibleActivities.add(i);
                currentActivity = i;
            }
        }
        return compatibleActivities;
    }

}
