package me.korbsti.morecrops.crop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import me.korbsti.morecrops.MoreCrops;

public class Crop {
	
	MoreCrops plugin;
	
	private String cropName;
	private String cropDisplayName;
	private ArrayList<String> textures = new ArrayList<String>();
	private ArrayList<String> potionEffects = new ArrayList<String>();
	private int hungerFeed;
	private int currentStage;
	private int growthSpeed;
	private int growthPerStage;
	private int subStage;
	private int stageAmount;
	private int id;
	private int dropAmount;
	private int dropSeedAmount;
	private boolean fullyGrown;
	private String underBlocks;
	private File cropFile;
	private YamlConfiguration cropYaml;
	private ArrayList<PlayerTextures> playerTextures = new ArrayList<PlayerTextures>();
	private ArrayList<PlayerProfile> playerProfiles = new ArrayList<PlayerProfile>();

	public Crop(MoreCrops instance) {
		this.plugin = instance;
	}
	
	public void setCropDisplayName(@Nonnull String displayName) {
		this.cropDisplayName = displayName;
	}
	
	public void setCropPotionEffect(@Nonnull ArrayList<String> cropPotionEffects) {
		this.potionEffects = cropPotionEffects;
	}
	
	public void setHungerFeed(@Nonnull int hungerFeed) {
		this.hungerFeed = hungerFeed;
	}
	
	public void setUnderBlocks(@Nonnull String underBlocks) {
		this.underBlocks = underBlocks;
	}
	
	public void setDropSeedAmount(@Nonnull int dropSeedAmount) {
		this.dropSeedAmount = dropSeedAmount;
	}
	
	public void setFullyGrown(@Nonnull boolean fullyGrown) {
		this.fullyGrown = fullyGrown;
	}
	
	public void setPlayerProfile(@Nonnull ArrayList<PlayerProfile> profiles) {
		this.playerProfiles = profiles;
	}
	
	public void setPlayerTextures(@Nonnull ArrayList<PlayerTextures> textures) {
		this.playerTextures = textures;
	}
	
	public void setCropName(@Nonnull String cropName) {
		this.cropName = cropName;
	}
	
	public void setSubStage(@Nonnull int subStage) {
		this.subStage = subStage;
	}
	
	public void setDropAmount(@Nonnull int dropAmount) {
		this.dropAmount = dropAmount;
	}
	
	public void setCropID(@Nonnull int id) {
		this.id = id;
	}
	
	public void setTexture(@Nonnull ArrayList<String> textures) {
		this.textures = textures;
	}
	
	public void setGrowthSpeed(@Nonnull int growthSpeed){
		this.growthSpeed = growthSpeed;
	}
	
	public void setGrowthPerStage(@Nonnull int growthPerStage) {
		this.growthPerStage = growthPerStage;
	}
	
	public void setStageAmount(@Nonnull int stageAmount) {
		this.stageAmount = stageAmount;
	}
	
	public void setCurrentStage(@Nonnull int currentStage) {
		this.currentStage = currentStage;
	}
	
	
	public void setCropFile(@Nonnull File cropFile) {
		this.cropFile = cropFile;
	}
	
	public void setCropYaml() {
		this.cropYaml = YamlConfiguration.loadConfiguration(cropFile);
	}
	
	public boolean getFullyGrown() {
		return this.fullyGrown;
	}
	
	public String getCropName() {
		return this.cropName;
	}
	
	public int getCurrentStage() {
		return this.currentStage;
	}
	
	public ArrayList<String> getCropTextures(){
		return this.textures;
	}
	
	public ArrayList<PlayerProfile> getPlayerProfiles(){
		return this.playerProfiles;
	}
	
	public ArrayList<PlayerTextures> getPlayerTextures(){
		return this.playerTextures;
	}
	
	public int getDropSeedAmount() {
		return this.dropSeedAmount;
	}
	
	public File getCropFile() {
		return this.cropFile;
	}
	
	public YamlConfiguration getCropYaml() {
		return cropYaml;
	}
	
	public String getUnderBlocks() {
		return this.underBlocks;
	}
	
	public String getCropDisplayName() {
		return this.cropDisplayName;
	}
	
	public ArrayList<String> getPotionEffects(){
		return this.potionEffects;
	}
	
	public int getHungerFeed() {
		return this.hungerFeed;
	}
	
	public int getGrowthSpeed() {
		return this.growthSpeed;
	}
	
	public int getDropAmount() {
		return this.dropAmount;
	}
	
	public int getGrowthPerStage() {
		return this.growthPerStage;
	}
	
	public int getStageAmount() {
		return this.stageAmount;
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getSubStage() {
		return this.subStage;
	}
	
	
}
