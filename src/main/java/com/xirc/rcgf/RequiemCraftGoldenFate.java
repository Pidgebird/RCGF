package com.xirc.rcgf;

import com.xirc.rcgf.item.ModItemGroups;
import com.xirc.rcgf.item.ModItems;
import com.xirc.rcgf.entities.ModEntities;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequiemCraftGoldenFate implements ModInitializer {
	public static final String MOD_ID = "rcgf";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Register item groups, items, and entities
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModEntities.HAMON_BEAM.toString();
	}
}
