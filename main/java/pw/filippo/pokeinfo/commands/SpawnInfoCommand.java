package pw.filippo.pokeinfo.commands;

import java.util.List;
import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.spawning.SpawnInfo;
import com.pixelmonmod.pixelmon.api.spawning.SpawnSet;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnInfoPokemon;
import com.pixelmonmod.pixelmon.api.spawning.util.SetLoader;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import pw.filippo.pokeinfo.utils.SpawnFormatter;

public class SpawnInfoCommand implements ICommand {

    private final List<String> aliases;

	public SpawnInfoCommand() {
        aliases = Lists.newArrayList("spawninfo", "si");
	}
	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "spawninfo";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/spawninfo <name>";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 1) {
            sender.sendMessage(new TextComponentString(TextFormatting.RED + this.getUsage(sender)));
            return;
        }
		String pokemon = args[0].substring(0, 1).toUpperCase() + args[0].substring(1).toLowerCase();
		if(!EnumPokemon.hasPokemonAnyCase(pokemon)) {
	        sender.sendMessage(new TextComponentString(TextFormatting.RED + "Pokemon not found"));
	        return;
		}
		SpawnSet spawnSet = SetLoader.getDefaultSpawnSetFor(EnumPokemon.valueOf(pokemon));
		if(spawnSet.spawnInfos.isEmpty()) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Data not found"));
			return;
		}
		sender.sendMessage(new TextComponentString(TextFormatting.GOLD + "Showing " + pokemon + " spawn info"));
		for(SpawnInfo info: spawnSet.spawnInfos) {
			SpawnInfoPokemon info1 = (SpawnInfoPokemon) info;
			sender.sendMessage(new TextComponentString(SpawnFormatter.conditionFormat(info1)));
			/*if(info.anticondition!=null)
				sender.sendMessage(new TextComponentString(SpawnFormatter.conditionFormat(info.anticondition)));*/
			
		}
		

	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		List<String> pokes = new ArrayList<String>();
		for(EnumPokemon poke: EnumPokemon.values()) {
			if(poke.name.toLowerCase().startsWith(args[0].toLowerCase()) || args[0].equals(""))
				pokes.add(poke.name);
		}
		return pokes;
		
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
