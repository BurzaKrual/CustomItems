package WandVariations;

import me.burzakrual.customitems.LocationHelper;
import me.burzakrual.customitems.Main;
import me.burzakrual.customitems.Wand;
import me.burzakrual.customitems.ParticleEmiter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class LightningWand extends Wand {
    int lightningMaximumRange = 50;

    public LightningWand(Main main, String name, String rarity, int cost) {
        super(main, name, rarity, cost);
    }

    public void runAction(final Player player) {
        Set<Material> ignoredBlocks = new HashSet<>(Arrays.asList(new Material[] { Material.AIR, Material.CAVE_AIR }));
        List<Block> lineOfSightBlocks = player.getLineOfSight(ignoredBlocks, this.lightningMaximumRange);
        Location targetLocation = ((Block)lineOfSightBlocks.get(lineOfSightBlocks.size() - 1)).getLocation();
        final Location lightningLocation = LocationHelper.offsetLocation(targetLocation, new Vector(0.5F, 1.0F, 0.5F));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITCH_AMBIENT, 0.5F, 1.0F);
        player.getWorld().playSound(lightningLocation, Sound.ENTITY_ENDERMAN_AMBIENT, 1.0F, 1.0F);
        BukkitRunnable runnable = new BukkitRunnable() {
            public void run() {
                player.getWorld().strikeLightning(lightningLocation);
            }
        };
        runnable.runTaskLater((Plugin)this.main, 50L);
        ParticleEmiter.emitParticles(lightningLocation, Particle.PORTAL, 500, 1.0D, new Vector(0, 0, 0));
    }
}

