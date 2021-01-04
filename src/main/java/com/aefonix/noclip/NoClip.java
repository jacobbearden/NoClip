package com.aefonix.noclip;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoClip extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    this.getServer().getPluginManager().registerEvents(this, this);
  }

  @Override
  public void onDisable() {}

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();

    if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
      if (nearBlock(player)) {
        player.setGameMode(GameMode.SPECTATOR);
      } else {
        player.setGameMode(GameMode.CREATIVE);
      }
    }
  }

  public boolean nearBlock(Player player) {
    boolean isNearBlock = false;
    BlockFace[] surrounding = new BlockFace[] {
      BlockFace.NORTH,
      BlockFace.NORTH_EAST,
      BlockFace.EAST,
      BlockFace.SOUTH_EAST,
      BlockFace.SOUTH,
      BlockFace.SOUTH_WEST,
      BlockFace.WEST,
      BlockFace.NORTH_WEST,
      BlockFace.UP,
      BlockFace.DOWN
    };
    Location[] locationsToCheck = new Location[] {player.getLocation(), player.getLocation().add(0.0D, 1.0D, 0.0D)};

    for (Location location : locationsToCheck) {
      for (BlockFace blockFace : surrounding) {
        if (!location.getBlock().getRelative(blockFace, 1).isEmpty()) {
          isNearBlock = true;
        }
      }
    }

    return isNearBlock;
  }
}
