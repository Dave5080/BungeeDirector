package me.bungee.dave;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Dave5080 on 01/05/2017.
 */
public class BungeeDirector extends Plugin{

    private ConfigurationProvider provider;
    private Configuration config;
    @Override
    public void onEnable(){
        provider = ConfigurationProvider.getProvider(YamlConfiguration.class);
        try {
            config = provider.load(newConfig("config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        registerServers();



        getLogger().info("BungeeDirector successful enabled!");
    }

    private File newConfig(String name){
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), name);


        if (!file.exists()) {
            try (InputStream in = getResourceAsStream(name)) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                getLogger().info("Could not create the configuration file.");
                e.printStackTrace();
            }
        }
        return file;
    }

    private void registerServers(){
        List<String> servers = config.getStringList("servers");

        for(String s : servers){
            getProxy().getPluginManager().registerCommand(this, new ServerCommand(s));
        }
    }
}
