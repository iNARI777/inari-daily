package work.inarigo.lab.lb;

import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class SimpleWeightedLoadBalancer implements ILoadBalancer {

    private final TreeSet<Target> targets = new TreeSet<>();

    private int weightSum = 0;

    private final ThreadLocal<Random> randomThreadLocal = ThreadLocal.withInitial(Random::new);

//    private final Random random = new Random();

    public SimpleWeightedLoadBalancer(List<WeightedServer> servers) {
        for (WeightedServer server : servers) {
            Target target = new Target();
            target.id = server.getId();
            target.weight = server.getWeight();
            this.weightSum += server.getWeight();
            target.end = this.weightSum - 1;
            targets.add(target);
        }
    }

    @Override
    public String select() {
        Random random = randomThreadLocal.get();
        int i = random.nextInt(this.weightSum);
        Target selected = new Target();
        selected.end = i;
        Target target = targets.ceiling(selected);
        if (target != null) {
            return target.id;
        }
        return null;
    }

    static class Target implements Comparable<Target> {
        String id;
        int weight;
        int end;

        @Override
        public int compareTo(Target o) {
            return end - o.end;
        }
    }
}
