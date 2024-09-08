package com.dotnomi.stranded.config;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ConfigData {
    private Boolean debugMode = false;
    private List<Mission> missions = new ArrayList<>();

    public Boolean getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(Boolean debugMode) {
        this.debugMode = debugMode;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }
}
