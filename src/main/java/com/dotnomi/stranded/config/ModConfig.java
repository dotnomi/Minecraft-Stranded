package com.dotnomi.stranded.config;

import com.dotnomi.stranded.Stranded;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;

@SuppressWarnings("unused")
public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String CONFIG_NAME = Stranded.MOD_ID + ".json";
    private ConfigData configData;

    public void initializeConfig() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_NAME);
        File configFile = configPath.toFile();
        if (!configFile.exists()) {
            createDefaultConfig(configFile);
        }
        loadConfig(configFile);
    }

    private void createDefaultConfig(File configFile) {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Stranded.class.getResourceAsStream("/config/" + CONFIG_NAME)), StandardCharsets.UTF_8)) {
            Type type = new TypeToken<ConfigData>() {}.getType();
            configData = GSON.fromJson(reader, type);
        } catch (Exception exception) {
            Stranded.LOGGER.error("Error while creating default config", exception);
            configData = new ConfigData();
        }
        saveConfig(configFile);
    }

    private void loadConfig(File configFile) {
        try (Reader reader = new FileReader(configFile)) {
            Type type = new TypeToken<ConfigData>() {}.getType();
            configData = GSON.fromJson(reader, type);
        } catch (Exception exception) {
            Stranded.LOGGER.error("Error while loading config", exception);
            configData = new ConfigData();
        }
    }

    private void saveConfig(File configFile) {
        try (Writer writer = new FileWriter(configFile)) {
            GSON.toJson(configData, writer);
        } catch (Exception exception) {
            Stranded.LOGGER.error("Error while saving config", exception);
        }
    }

    public ConfigData getConfigData() {
        return configData;
    }

    public void setConfigData(ConfigData configData) {
        this.configData = configData;
    }
}
