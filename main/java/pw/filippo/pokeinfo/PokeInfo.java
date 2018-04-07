package pw.filippo.pokeinfo;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import pw.filippo.pokeinfo.proxy.IProxy;
import pw.filippo.pokeinfo.utils.RarityCalculator;


@Mod(modid = "pokeinfo", name = "PokeInfo", version = "0.0.1", dependencies = "required-after:pixelmon@[6.2.3,)")
public class PokeInfo {
	
    public static final String MODID = "pokeinfo";
    public static final String MODNAME = "PokeInfo";
    public static final String VERSION = "0.0.1";
	 
    @SidedProxy(clientSide = "pw.filippo.pokeinfo.proxy.ClientProxy")
    public static IProxy proxy;

    @Mod.Instance
    public static PokeInfo instance;

    public static Logger logger;
    
    public static RarityCalculator rc;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.log(Level.INFO, "PokeInfo Initialized");
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
		rc = new RarityCalculator();
    }
}
