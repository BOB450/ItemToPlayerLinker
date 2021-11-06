package LinkerPkg;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {
    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this,this);
        getLogger().info("LINKER PLUGIN HAS BEEN ENABLED! by BOB450");

    }
        //Make a loop that iterates through all player on server and run code below to check for dragon egg.
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("lol")) {
            Player p = (Player) sender;
            for (ItemStack is : p.getInventory()){
                if (is.getType() == Material.DIRT){
                    p.sendMessage("You have dirt");
                }
            }

        }
        return true;
    }
}
