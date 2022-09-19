package pluginprovider.objects;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.scheduler.Tasker;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class DropExecutor {

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Secured
    public static final List<String> securedActions = Arrays.asList(
                    "ActionBar",
                    "Title",
                    "Broadcast",
                    "Message",
                    "CustomCommands"
            );

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    // Values
    private final BukkitRunnable action;

    // Constructor
    public DropExecutor(Config c, Factors f, CachedAttributes a) {
        action = new BukkitRunnable() {
            @Override
            public void run() {
                new Tasker() {
                    @Override
                    public void run() {

                    }
                }.runTask();
            }
        };
    }

    // Executor
    public void execute() {
        action.run();
    }

    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

}
