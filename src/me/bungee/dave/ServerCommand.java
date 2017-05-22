package me.bungee.dave;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Dave5080 on 15/05/2017.
 */
public class ServerCommand extends Command{

    private String server;
    private String cmdName;

    public ServerCommand(String server) {
        super(server.toLowerCase()+"server");
        this.server = server;
        this.cmdName = server.toLowerCase()+"server";
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer){
            ProxiedPlayer p = (ProxiedPlayer) commandSender;
            if(!p.getServer().getInfo().getName().equalsIgnoreCase(server)){
                ServerInfo target = ProxyServer.getInstance().getServerInfo(server);
                p.connect(target);
            }else
                p.sendMessage(new ComponentBuilder("You're already in that server!").color(ChatColor.RED).create());

        }else
            commandSender.sendMessage(new ComponentBuilder("This command can be executed only by an in game player!").color(ChatColor.RED).create());
    }
}
