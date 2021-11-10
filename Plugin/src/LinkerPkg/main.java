package LinkerPkg;


import net.minecraft.nbt.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
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


    public static void createWand(Player PL) {
        ItemStack item = new ItemStack(Material.OAK_WOOD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PL.getDisplayName());
        meta.setLocalizedName(PL.getName());
        List<String> lore = new ArrayList<>();
        lore.add(PL.getName());
        meta.setLore(lore);
        meta.setLocalizedName(PL.getName());

       // meta.addEnchant(Enchantment.LUCK, 1, false);
       // meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        wand = item;
/*
        net.minecraft.world.item.ItemStack nmsApple = CraftItemStack.asNMSCopy(wand);
        NBTTagCompound tag = nmsApple.getOrCreateTag();
        NBTTagList Modi = new NBTTagList();
        NBTTagCompound UUId = new NBTTagCompound();
        UUId.setString("id", PL.getName());
        Modi.add(UUId);
        tag.set("NBTT",Modi);
        nmsApple.setTag(tag);
        */


    }

    public void doBreakStuff (Block block)
    {
      //  Bukkit.broadcastMessage("The blocks type is" + block);
       // Bukkit.broadcastMessage( block.getBlockData().getAsString());
        Block b = event.getBlock(block);
        ItemMeta meta = b.getItemMeta();
        ItemStack is = new ItemStack(b.getType(), 1);
        is.setItemMeta(meta);
        for(Player p : Bukkit.getOnlinePlayers())
        {
            if(p.getName() == block.getMetadata(LocalizedName))
            {

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
