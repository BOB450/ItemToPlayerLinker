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
        ItemStack item = new ItemStack(Material., 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PL.getDisplayName());
        List<String> lore = new ArrayList<>();
        lore.add("§7This is your Egg, keep it SAFE!");
        meta.setLore(lore);

       // meta.addEnchant(Enchantment.LUCK, 1, false);
       // meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        wand = item;
    }

    public void doBreakStuff (Block block)
    {
      //  Bukkit.broadcastMessage("The blocks type is" + block);
        Bukkit.broadcastMessage( block.getBlockData().getAsString());
        for(Player p : Bukkit.getOnlinePlayers())
        {
            if(p.getDisplayName() == block.getBlockData().getAsString())
            {
                Bukkit.broadcastMessage(p.getDisplayName() + "jsdfklsdfd");
            }
        }

    }


    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this,this);
        getLogger().info("LINKER PLUGIN HAS BEEN ENABLED! by BOB450");

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player plr = event.getPlayer();
        createWand(plr);

      //  event.setJoinMessage(ChatColor.AQUA + "Welcome blank server " + plr.getDisplayName() + " use the /helpsmp command to get more info " );
        if(plr.hasPlayedBefore() == true) {
            plr.getInventory().addItem(wand);

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
