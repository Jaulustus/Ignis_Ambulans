package com.example.ignis_ambulans.events;

import com.example.ignis_ambulans.IgnisAmbulansMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IgnisAmbulansMod.MODID)
public class IgnisAmbulansEvents {
    
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level();
            
            if (!player.onGround()) return;
            
            int enchLevel = EnchantmentHelper.getEnchantmentLevel(IgnisAmbulansMod.IGNIS_AMBULANS.get(), player);
            if (enchLevel <= 0) return;
            
            int radius = getRadiusForLevel(enchLevel);
            BlockPos pos = player.blockPosition().below();
            
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x*x + z*z <= radius*radius) {
                        BlockPos icePos = pos.offset(x, 0, z);
                        if (level.getFluidState(icePos).is(Fluids.LAVA)) {
                            level.setBlockAndUpdate(icePos, Blocks.ICE.defaultBlockState());
                        }
                    }
                }
            }
        }
    }
    
    private static int getRadiusForLevel(int level) {
        return switch (level) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            default -> 1;
        };
    }
} 