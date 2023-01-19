package cn.jerrymc.jrgames;

import java.util.logging.Logger;

public class LOGGER {
    public static Logger logger;
    public static void debug(String message){
        if(Jrgames.plugin.getConfig().getBoolean("debug",false)){
            logger.info("[debug] "+message);
        }
    }
}
