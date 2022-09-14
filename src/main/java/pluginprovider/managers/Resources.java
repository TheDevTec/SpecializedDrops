package pluginprovider.managers;

import me.devtec.shared.dataholder.Config;
import me.devtec.shared.dataholder.DataType;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class Resources {

    public static void queryFile(Plugin p, String fileName) {
        if (!p.getDataFolder().exists()) p.getDataFolder().mkdir();
        File[] files = p.getDataFolder().listFiles();
        boolean save = true;
        assert files != null;
        for (File var : files) {
            String name = var.getName();
            if (fileName.equalsIgnoreCase(name)) save = false;
        }
        p.saveResource(fileName, save);
    }

    public static void queryFolder(Plugin p, String fileName) {
        try {
            if (!p.getDataFolder().exists()) p.getDataFolder().mkdir();
            File[] files = p.getDataFolder().listFiles();
            boolean save = true;
            assert files != null;
            for (File var : files) {
                String name = fileName.replace("/", "");
                if (name.equalsIgnoreCase(var.getName())) save = false;
            }
            if (save) {
                File pluginFile = new File(p.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
                JarFile jar = new JarFile(pluginFile);
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    if (entry.getName().startsWith(fileName) && !entry.getName().endsWith("/")) {
                        Config.loadFromPlugin(p.getClass(), entry.getName(), p.getDataFolder().toPath() + "/" + entry.getName()).save(DataType.YAML);
                    }
                }
                jar.close();
            }
        } catch (IOException | URISyntaxException exception) {}
    }

}
