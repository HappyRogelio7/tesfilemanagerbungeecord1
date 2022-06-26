import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class FileManager {
  
  
    /*
    FileManager Link: https://github.com/HappyRogelio7/tesfilemanagerbungeecord1
    */

    private Configuration config;

    public static Configuration getConfig() {
        FileManager file = new FileManager();
        return file.config;
    }
    
    public File createFile(Plugin plugin, String resource) {
        File folder = plugin.getDataFolder();
        if (!folder.exists())
            folder.mkdirs();
        File resourceFile = new File(folder, resource);
        try {
            if (!resourceFile.exists()) {
                resourceFile.createNewFile();
                try(InputStream in = plugin.getResourceAsStream(resource);
                    OutputStream out = new FileOutputStream(resourceFile)) {
                    ByteStreams.copy(in, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resourceFile;
    }

    public void loadFile(String a){
        try {
            
            config = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(createFile((Plugin) "plugin instance", "filename"+".yml"));
            
        } catch (IOException e) {
            e.printStackTrace();
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Error occurred while load files.");
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, " ");
            ProxyServer.getInstance().getLogger().log(Level.SEVERE, "Error: " + e.getMessage());
            return;
        }
    }
}
