package cn.jerrymc.jrgames;

import cn.jerrymc.jrgames.lib.JrgamesPapiExpansion;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Locale;

public class PAPIExpansion extends PlaceholderExpansion {
    private final HashMap<String, JrgamesPapiExpansion> map = new HashMap<>();

    public void registerJrgamesPapiExpansion(String name, JrgamesPapiExpansion expansion) {
        if (map.containsKey(name)) {
            LOGGER.logger.warning(String.format("无法注册jrgames PAPI扩展 %s, 它已经被注册了", name));
            return;
        }
        map.put(name, expansion);
        LOGGER.logger.info("注册了jrgames的papi扩展 " + name);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "jrgames";
    }

    @Override
    public @NotNull String getAuthor() {
        return PluginInfo.author;
    }

    @Override
    public @NotNull String getVersion() {
        return PluginInfo.version;
    }

    @Override
    public boolean persist() {
        // reload时需要重新加载
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        String key = params.toLowerCase(Locale.ROOT).replaceFirst(String.format("%s_", getIdentifier()), "");
        if (map.containsKey(key)) {
            return map.get(key).onRequest(player);
        }
        // 未知, 交给其他东西处理
        return null;
    }
}
