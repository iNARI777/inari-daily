package work.inarigo.lab.lb;

import java.util.ArrayList;
import java.util.List;

public class NginxLoadBalancer implements ILoadBalancer {

    private final List<Target> targets = new ArrayList<>();

    public NginxLoadBalancer(List<WeightedServer> servers) {
        for (WeightedServer server : servers) {
            Target target = new Target();
            target.id = server.getId();
            target.weight = server.getWeight();
            target.effectiveWeight = server.getWeight();
            target.currentWeight = 0;

            targets.add(target);
        }
    }

    @Override
    public synchronized String select() {

        int total = 0;
        Target best = null;

        for (int i = 0; i < targets.size(); i++) {
            Target t = targets.get(i);

            t.currentWeight += t.effectiveWeight;
            total += t.effectiveWeight;
            if (t.effectiveWeight < t.weight) {
                t.effectiveWeight++;
            }
            if (best == null || t.currentWeight > best.currentWeight) {
                best = t;
            }
        }
        best.currentWeight -= total;
        return best.id;
    }

    static class Target {
        String id;
        int weight;
        int currentWeight;
        int effectiveWeight;
    }
}
