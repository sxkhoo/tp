package sort;

import event.Event;
import label.Priority;
import java.util.ArrayList;

public class SortByPriority extends Sort {

    @Override
    public void sort(ArrayList<Event> events, ArrayList<String> priorities) {
        int n = events.size();
        for (int i = 0; i < n - 1; i++) {
            int bestIdx = i;
            for (int j = i + 1; j < n; j++) {
                int prioBest = Priority.getValue(priorities.get(bestIdx));
                int prioJ = Priority.getValue(priorities.get(j));

                boolean isHigherPriority = prioJ > prioBest;
                boolean isSamePriorityEarlierEnd = prioJ == prioBest &&
                        events.get(j).getEndTime().isBefore(events.get(bestIdx).getEndTime());

                if (isHigherPriority || isSamePriorityEarlierEnd) {
                    bestIdx = j;
                }
            }
            swap(events, priorities, i, bestIdx);
        }
    }

    private void swap(ArrayList<Event> events, ArrayList<String> priorities, int i, int j) {
        Event tempEvent = events.get(i);
        events.set(i, events.get(j));
        events.set(j, tempEvent);

        String tempPrio = priorities.get(i);
        priorities.set(i, priorities.get(j));
        priorities.set(j, tempPrio);
    }
}
