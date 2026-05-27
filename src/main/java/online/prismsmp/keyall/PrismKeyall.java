package online.prismsmp.keyall;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Random;

public class PrismKeyall extends JavaPlugin implements CommandExecutor {

    private final Random random = new Random();

    private final String[] CRATES = {
        "Common","Common","Common","Common","Common",
        "Common","Common","Common","Common","Common",
        "Common","Common","Common","Common","Common",
        "Common","Common","Common","Common","Common",
        "Common","Common","Common","Common","Common",
        "Common","Common","Common","Common","Common",
        "Common","Common","Common","Common","Common",
        "Common","Common","Common","Common","Common",
        "Common","Common","Common","Common","Common",
        "Common","Common","Common","Common","Common",
        "Uncommon","Uncommon","Uncommon","Uncommon","Uncommon",
        "Uncommon","Uncommon","Uncommon","Uncommon","Uncommon",
        "Uncommon","Uncommon","Uncommon","Uncommon","Uncommon",
        "Uncommon","Uncommon","Uncommon","Uncommon","Uncommon",
        "Uncommon","Uncommon","Uncommon","Uncommon","Uncommon",
        "Rare","Rare","Rare","Rare","Rare",
        "Rare","Rare","Rare","Rare","Rare",
        "Rare","Rare","Rare","Rare","Rare",
        "Epic","Epic","Epic","Epic","Epic",
        "Epic","Epic",
        "Legendary","Legendary","Legendary"
    };

    @Override
    public void onEnable() {
        getCommand("keyall").setExecutor(this);
        long interval = 5L * 60 * 60 * 20;
        Bukkit.getScheduler().runTaskTimer(this, this::runKeyall, interval, interval);
        getLogger().info("PrismKeyall enabled! Keyall runs every 5 hours.");
    }

    private void runKeyall() {
        int count = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            String crate = CRATES[random.nextInt(CRATES.length)];
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "crazycrates give virtual " + crate + " 1 " + player.getName());
            count++;
        }
        // Reset EzCountdown timer
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ezcountdown reset keyall");
        
        if (count > 0) {
            Bukkit.broadcastMessage("§8[§6Prism SMP§8] §r§eAll §6" + count + " §eonline players received a random key! Head to §6/spawn §eto use your crates!");
        }
        getLogger().info("Keyall ran for " + count + " players.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("prismsmp.keyall")) {
            sender.sendMessage("§cNo permission.");
            return true;
        }
        runKeyall();
        sender.sendMessage("§aKeyall executed!");
        return true;
    }
}
