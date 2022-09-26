package pluginprovider.coreapi;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoreMessage {

    // Constructors
    private final List<BaseComponent> components = new ArrayList<>();
    public CoreMessage() {}

    // Functions
    public void addText(String text) {
        components.addAll(Arrays.asList(TextComponent.fromLegacyText(text.replace("&", "§"))));
    }
    public void addText(String text, String hover) {
        BaseComponent[] component = TextComponent.fromLegacyText(text.replace("&", "§"));
        for (BaseComponent var : component) {
            if (hover!=null) var.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(hover.replace("&", "§"))));
            components.add(var);
        }
    }
    public void addText(String text, String hover, ClickEvent.Action action, String clickEventValue) {
        BaseComponent[] component = TextComponent.fromLegacyText(text.replace("&", "§"));
        for (BaseComponent var : component) {
            if (hover!=null) var.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(hover.replace("&", "§"))));
            if (action!=null && clickEventValue!=null) var.setClickEvent(new ClickEvent(action, clickEventValue));
            components.add(var);
        }
    }
    public BaseComponent[] toMessage() {
        return components.toArray(new BaseComponent[0]);
    }

    // Send
    public void sendTo(Player p) {
        p.spigot().sendMessage(toMessage());
    }
    public void broadcast() {
        Bukkit.spigot().broadcast(toMessage());
    }

}
