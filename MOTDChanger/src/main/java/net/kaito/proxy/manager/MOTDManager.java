package net.kaito.proxy.manager;

import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MOTDManager {

    @Getter
    private final List<String> motds;
    @Getter
    private int currentIndex = 0;
    private final Lock lock = new ReentrantLock();

    public MOTDManager(List<String> motds) {
        this.motds = Collections.unmodifiableList(motds);
        if (this.motds.isEmpty()) {
            throw new IllegalArgumentException("MOTD list cannot be empty");
        }
    }

    public String getCurrentMOTD() {
        lock.lock();
        try {
            return motds.get(currentIndex);
        } finally {
            lock.unlock();
        }
    }

    public void cycleMOTD() {
        lock.lock();
        try {
            currentIndex = (currentIndex + 1) % motds.size();
        } finally {
            lock.unlock();
        }
    }

}