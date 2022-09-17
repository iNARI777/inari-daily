package work.inari.daily.spring.bean;

import org.springframework.stereotype.Component;

@Component
public class LoopB {
    private LoopA loopA;

    public LoopA getLoopA() {
        return loopA;
    }

    public void setLoopA(LoopA loopA) {
        this.loopA = loopA;
    }
}
