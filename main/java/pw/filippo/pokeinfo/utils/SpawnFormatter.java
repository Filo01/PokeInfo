package pw.filippo.pokeinfo.utils;

import java.util.Map;
import java.util.Map.Entry;

import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnInfoPokemon;
import com.pixelmonmod.pixelmon.api.spawning.conditions.SpawnCondition;

import net.minecraft.util.text.TextFormatting;
import pw.filippo.pokeinfo.PokeInfo;

public class SpawnFormatter {
	public static String conditionFormat(SpawnInfoPokemon info) {
		SpawnCondition condition = info.condition;
		StringBuilder sb = new StringBuilder();
		sb.append(TextFormatting.GREEN + "Biomes:" + TextFormatting.WHITE);
		Map<String, Double> biomeRarity = PokeInfo.rc.calculateRarity(info);
		for(Entry<String, Double> rarity: biomeRarity.entrySet()) {
			
			sb.append(" " + rarity.getKey() + "(" + formatRarity(rarity.getValue()) + "),");
		}
		sb.setLength(sb.length() - 1);
		sb.append("\n");
		if(condition.neededNearbyBlocks != null && !condition.neededNearbyBlocks.isEmpty())
			sb.append(TextFormatting.GREEN + "Nearby Blocks: " + TextFormatting.WHITE + condition.neededNearbyBlocks.toString().toLowerCase() + "\n");
		if(condition.baseBlocks != null && !condition.baseBlocks.isEmpty())
			sb.append(TextFormatting.GREEN + "Base Blocks: " + TextFormatting.WHITE + condition.baseBlocks.toString().toLowerCase() + "\n");
		if(condition.weathers != null && !condition.weathers.isEmpty())
			sb.append(TextFormatting.GREEN + "Weathers: " + TextFormatting.WHITE + condition.weathers.toString().toLowerCase() + "\n");
		if(condition.times != null && !condition.times.isEmpty())
			sb.append(TextFormatting.GREEN + "Times: " + TextFormatting.WHITE + condition.times.toString().toLowerCase() + "\n");
		if(info.stringLocationTypes!=null && !info.stringLocationTypes.isEmpty()) {
			sb.append(TextFormatting.GREEN + "LocationTypes: " + TextFormatting.WHITE );
			for(String locType: info.stringLocationTypes) sb.append(locType + ", ");
			sb.append("\n");
		}
		if(info.minLevel!=0)
			sb.append(TextFormatting.GREEN + "Min lvl: " + TextFormatting.WHITE + info.minLevel + "\n");
		if(info.maxLevel!=0)
			sb.append(TextFormatting.GREEN + "Max lvl: " + TextFormatting.WHITE + info.maxLevel + "\n");
		if(info.interval!=null)
			sb.append(TextFormatting.GREEN + "Interval: " + TextFormatting.WHITE + info.interval + "\n");
		if(condition.maxX!=null)
			sb.append(TextFormatting.GREEN + "Max X: " + TextFormatting.WHITE + condition.maxX + " ");
		if(condition.minX!=null)
			sb.append(TextFormatting.GREEN + "min X: " + TextFormatting.WHITE + condition.minX + " ");
		if(condition.maxY!=null)
			sb.append(TextFormatting.GREEN + "max Y: " + TextFormatting.WHITE + condition.maxY + " ");
		if(condition.minY!=null)
			sb.append(TextFormatting.GREEN + "min Y: " + TextFormatting.WHITE + condition.minY + "\n");
		if(condition.seesSky!=null)
			sb.append(TextFormatting.GREEN + "Sees sky: " + TextFormatting.WHITE + condition.seesSky + "\n");
		if(condition.maxLightLevel!=null)
			sb.append(TextFormatting.GREEN + "Max light: " + TextFormatting.WHITE + condition.maxLightLevel + " ");
		if(condition.minLightLevel!=null)
			sb.append(TextFormatting.GREEN + "min light: " + TextFormatting.WHITE + condition.minLightLevel + "\n");
		if(condition.moonPhase!=null)
			sb.append(TextFormatting.GREEN + "Moon phase: " + TextFormatting.WHITE + condition.moonPhase + "\n");
		return sb.toString().trim();
	}

	private static String formatRarity(double rarity) {
		double greenThreshold = 10.0D;
	    double yellowThreshold = 2.0D;
	    double redThreshold = 0.5D;
	    StringBuilder sb = new StringBuilder();
	    
		if(rarity>=greenThreshold) {
			sb.append(TextFormatting.GREEN);
			sb.append(String.format("%.2f", rarity) + "%" + TextFormatting.WHITE);
		}else if(rarity>=yellowThreshold) {
			sb.append(TextFormatting.YELLOW);
			sb.append(String.format("%.2f", rarity) + "%" + TextFormatting.WHITE);
		}else if(rarity>=redThreshold) {
			sb.append(TextFormatting.RED);
			sb.append(String.format("%.2f", rarity) + "%" + TextFormatting.WHITE);
		}else {
			sb.append(TextFormatting.LIGHT_PURPLE);
			sb.append(String.format("%.3f", rarity) + "%" + TextFormatting.WHITE);
		}
		return sb.toString();
	}

}
