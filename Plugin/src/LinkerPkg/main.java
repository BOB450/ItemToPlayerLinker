package LinkerPkg;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {
    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this,this);
        getLogger().info("LINKER PLUGIN HAS BEEN ENABLED! by BOB450");

    }
}
