package pw.filippo.pokeinfo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.pixelmonmod.pixelmon.api.spawning.SpawnInfo;
import com.pixelmonmod.pixelmon.api.spawning.util.SetLoader;
import com.pixelmonmod.pixelmon.enums.EnumPokemon;

import net.minecraft.world.biome.Biome;

public class RarityCalculator {
	private Map<String, Double> biomeRaritySum;
	public RarityCalculator() {
		biomeRaritySum = new HashMap<String, Double>();
		for(EnumPokemon poke: EnumPokemon.values()) {
			List<SpawnInfo> pokeInfos = SetLoader.getDefaultSpawnSetFor(poke).spawnInfos;
			double rarity = 0;
			for(SpawnInfo pokeInfo: pokeInfos) {
				rarity = pokeInfo.rarity;
				for(Biome biome: pokeInfo.condition.biomes) {
					String biomeName = biome.getBiomeName();
					double raritySum = biomeRaritySum.getOrDefault(biomeName, 0.0);
					biomeRaritySum.put(biomeName, raritySum+rarity);
				}
			}
		}
	}
	public Map<String, Double> calculateRarity(SpawnInfo spawnInfo) {
		
		double rarity = spawnInfo.rarity;
		List<String> biomes = new ArrayList<String>();

		for(Biome biome: spawnInfo.condition.biomes) {
			biomes.add(biome.getBiomeName());
		}
		Map<String, Double> result = new HashMap<String, Double>();
		for(Entry<String, Double> raritySum: biomeRaritySum.entrySet()) {
			if(biomes.contains(raritySum.getKey())){
				double percentage = (rarity/raritySum.getValue())*100;
				System.out.println(raritySum.getKey() + " " + raritySum.getValue() + " " + percentage);
				result.put(raritySum.getKey(), percentage);
			}
		}
		return result;
	}
}
