package net.kaito.proxy.task;

import dev.waterdog.waterdogpe.scheduler.Task;
import net.kaito.proxy.manager.MOTDManager;

public class MOTDTask extends Task {

    private final MOTDManager motdManager;

    public MOTDTask(MOTDManager motdManager) {
        this.motdManager = motdManager;
    }

    @Override
    public void onRun(int currentTick) {
        motdManager.cycleMOTD();
    }

    /**
     * This method is invoked when the task is canceled.
     * It can be overridden to perform any necessary cleanup or logging.
     */

    @Override
    public void onCancel() {
        // Optional: Add any cleanup or logging code here if needed
    }
}
