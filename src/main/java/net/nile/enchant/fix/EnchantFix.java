package net.nile.enchant.fix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.handler.logging.LogLevel;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.AnvilScreenHandler;

public class EnchantFix implements ModInitializer{

    public static final Logger logger = LogManager.getLogger();

    //public static final int BIG_NUMBER = Integer.MAX_VALUE;

    @Override
    public void onInitialize() {
        logger.error("your life is a mistake");
    }
    
}
