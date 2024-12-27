package com.example.ignis_ambulans.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class IgnisAmbulansEnchantment extends Enchantment {
    public IgnisAmbulansEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public int getMinCost(int level) {
        return level * 10;  // 10 für Level 1, 20 für Level 2, 30 für Level 3
    }

    @Override
    public int getMaxCost(int level) {
        return getMinCost(level) + 10;  // Ein bisschen höher als MinCost
    }
} 