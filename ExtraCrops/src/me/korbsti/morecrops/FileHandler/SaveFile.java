package me.korbsti.morecrops.FileHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import me.korbsti.morecrops.MoreCrops;
import me.korbsti.morecrops.crop.Crop;

public class SaveFile {
	
	MoreCrops plugin;
	
	public SaveFile(MoreCrops instance) {
		this.plugin = instance;
	}
	
	public void saveFile(File f, Crop crop) throws IOException {
		if (f.exists()) {
			if(f.getTotalSpace() == 0) {
				f.delete();
				plugin.cropHash.remove(f);
				return;
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f, false));
			writer.write("id: " + crop.getID() + System.lineSeparator());
			writer.write("currentStage: " + crop.getCurrentStage() + System.lineSeparator());
			writer.write("growthSpeed: " + crop.getGrowthSpeed() + System.lineSeparator());
			writer.write("growthPerStage: " + crop.getGrowthPerStage() + System.lineSeparator());
			writer.write("subStage: " + crop.getSubStage() + System.lineSeparator());
			writer.write("dropAmount: " + crop.getDropAmount() + System.lineSeparator());
			writer.write("stageAmount: " + crop.getStageAmount() + System.lineSeparator());
			writer.write("underBlocks: " + crop.getUnderBlocks() + System.lineSeparator());
			writer.write("textures:" + System.lineSeparator());
			for (String str : crop.getCropTextures()) {
				writer.write("- " + str + System.lineSeparator());
			}
			writer.write("displayName: " + "\'" + crop.getCropDisplayName() + "\'" + System.lineSeparator());
			writer.write("potionEffects:" + System.lineSeparator());
			for (String str : crop.getPotionEffects()) {
				writer.write("- " + str + System.lineSeparator());
			}
			writer.write("hungerFeed: " + crop.getHungerFeed() + System.lineSeparator());
			writer.write("dropSeedAmount: " + crop.getDropSeedAmount() + System.lineSeparator());
			writer.write("fullyGrown: " + crop.getFullyGrown() + System.lineSeparator());

			writer.flush();
			writer.close();
			crop.setCropYaml();
		}
		
	}
	
}
