package pw.filippo.pokeinfo.proxy;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pw.filippo.pokeinfo.commands.SpawnInfoCommand;

@SideOnly(Side.CLIENT)
public class ClientProxy implements IProxy {
    public void preInit(FMLPreInitializationEvent e) {
        
	}

	public void init(FMLInitializationEvent e) {	
		CommandHandler commandHandler = ClientCommandHandler.instance;
		commandHandler.registerCommand(new SpawnInfoCommand());
	}

	public void postInit(FMLPostInitializationEvent e) {		
	}
}