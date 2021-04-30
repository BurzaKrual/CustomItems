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
import org.bukkit.util.Vector;

public class TeleportWand extends Wand {
    int teleportMaximumRange = 30;

    public TeleportWand(Main main, String name, String rarity, int cost) {
        super(main, name, rarity, cost);
    }

    public void runAction(Player player) {
        Set<Material> ignoredBlocks = new HashSet<>(Arrays.asList(new Material[] { Material.AIR, Material.CAVE_AIR }));
        List<Block> lineOfSightBlocks = player.getLineOfSight(ignoredBlocks, this.teleportMaximumRange);
        Location targetLocation = ((Block)lineOfSightBlocks.get(lineOfSightBlocks.size() - 1)).getLocation();
        Location teleportLocation = new Location(
                targetLocation.getWorld(),
                targetLocation.getX() + 0.5D,
                targetLocation.getY() + 1.0D,
                targetLocation.getZ() + 0.5D,
                player.getLocation().getYaw(),
                player.getLocation().getPitch());
        ParticleEmiter.emitParticles(
                LocationHelper.offsetLocation(player.getLocation(), new Vector(0, 1, 0)),
                Particle.DRAGON_BREATH, 100, 0.01D, new Vector(0.25D, 0.5D, 0.25D));
        ParticleEmiter.emitParticles(
                LocationHelper.offsetLocation(teleportLocation, new Vector(0, 1, 0)),
                Particle.DRAGON_BREATH, 100, 0.01D, new Vector(0.25D, 0.5D, 0.25D));
        player.teleport(teleportLocation);
        player.getWorld().playSound(teleportLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
    }
}
