package LinkerPkg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class main extends JavaPlugin implements Listener {


    public static ItemStack wand;


    private static void createWand(Player PL) {
        ItemStack item = new ItemStack(Material.DRAGON_EGG, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("");
        List<String> lore = new ArrayList<>();
        lore.add("§7This is your Egg keep it SAFE!");
        meta.setLore(lore);
       // meta.addEnchant(Enchantment.LUCK, 1, false);
       // meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        wand = item;
    }

    public void doBreakStuff (Block block)
    {

        Bukkit.broadcastMessage("The blocks type is" + block);
    }


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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player plr = event.getPlayer();
        createWand(plr);

        event.setJoinMessage(ChatColor.AQUA + "Welcome blank server " + plr.getDisplayName() + " use the /helpsmp command to get more info " );
        if(plr.hasPlayedBefore() == true) {
            ItemStack i  = new ItemStack(Material.DRAGON_EGG);
           i.getItemMeta().setDisplayName("Costom egg");
            plr.getInventory().addItem(wand);
            for (ItemStack is : plr.getInventory()){
                if (is.getType() == Material.DRAGON_EGG){
                    plr.sendMessage("You have a dragon egg");
                }
            }
        }

        //Bukkit.broadcastMessage(plr + "Has joined the serve ");

    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        doBreakStuff(event.getBlock());
        //Your Code Here
    }


}
