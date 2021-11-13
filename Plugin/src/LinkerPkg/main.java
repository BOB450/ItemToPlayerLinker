package LinkerPkg;


import net.minecraft.nbt.*;
import org.bukkit.*;
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
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class main extends JavaPlugin implements Listener {

    public ArrayList<Location> BlockLoc = new ArrayList<Location>();//list of egg locations
    public ArrayList<String> Players = new ArrayList<String>();//list of players that have a egg placed in the world
    public ArrayList<String> OffSpec = new ArrayList<String>();//player is added to list if egg is broken while offline
    public ArrayList<String> DieList = new ArrayList<String>();//added if egg is broken

    public static ItemStack wand;


    public static void createWand(Player PL) {
        ItemStack item = new ItemStack(Material.OAK_WOOD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PL.getDisplayName() + "Egg");
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

    public void doBreakStuff (Block blockz)
    {
      //  Bukkit.broadcastMessage("The blocks type is" + block);
       // Bukkit.broadcastMessage( block.getBlockData().getAsString());
        Location qloc = blockz.getLocation();
        for (int i = 0; i < BlockLoc.size(); i++)
        {
            if(qloc.equals(BlockLoc.get(i)) && blockz.getType() == Material.OAK_WOOD)
            {


                String plrid = Players.get(i);
                Bukkit.broadcastMessage("Player " + Players.get(i) + " lost there egg");
                for(Player p : Bukkit.getOnlinePlayers()){
                   if(p.getName() == plrid)
                   {
                     //  p.setGameMode(GameMode.SPECTATOR);
                       DieList.add(p.getName());
                       blockz.breakNaturally();
                       Players.remove(i);
                       BlockLoc.remove(i);
                       return;
                   }
                }
                for(OfflinePlayer p2 : Bukkit.getOfflinePlayers()){
                    if(p2.getName() == plrid)
                    {
                        OffSpec.add(p2.getName());
                        blockz.breakNaturally();
                        Players.remove(i);
                        BlockLoc.remove(i);
                    }
                }
            }
        }
    }
    public void placeStuff (Block block, Player pl)
    {
        if(block.getType() == Material.OAK_WOOD) {
            Location Loc = block.getLocation();
            BlockLoc.add(Loc);
            Players.add(pl.getName());
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
        for (int i2 = 0; i2 < OffSpec.size(); i2++) {
            if (plr.getName().equals(OffSpec.get(i2))) {
               // plr.setGameMode(GameMode.SPECTATOR);
                DieList.add(plr.getName());
                OffSpec.remove(i2);
            }
        }
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

    @EventHandler
    public  void onBlockExplode(BlockExplodeEvent event23)
    {
        Bukkit.broadcastMessage("expllload");
        doBreakStuff(event23.getBlock());
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event2)
    {
        placeStuff(event2.getBlock() , event2.getPlayer());
        //Your Code Here
    }

    @EventHandler
    public void onplayerDeath(PlayerDeathEvent event){
        Player player9 = event.getEntity();
        for (int i3 = 0; i3 < DieList.size(); i3++)
        if(player9.getName().equals(DieList.get(i3))){
            player9.setGameMode(GameMode.SPECTATOR);
            Bukkit.broadcastMessage(ChatColor.AQUA +"Player" +player9.getDisplayName() +"is out of the game");
        }

        if(player9.getInventory().contains(Material.OAK_WOOD))
        {
            Bukkit.broadcastMessage(ChatColor.AQUA +"Player" +player9.getDisplayName() +"Has lost there egg");

            // player9.setGameMode(GameMode.SPECTATOR);
            DieList.add(player9.getName());
        }

    }

}
