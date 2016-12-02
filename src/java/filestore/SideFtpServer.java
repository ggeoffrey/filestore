package filestore;

import filestore.CustomCommands.*;
import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.command.CommandFactoryFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.util.ArrayList;
import java.util.List;


class SideFtpServer{

    public static FtpServer start(){

        FtpServerFactory serverFactory = new FtpServerFactory();
        ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
        connectionConfigFactory.setAnonymousLoginEnabled(true);

        serverFactory.setConnectionConfig(connectionConfigFactory.createConnectionConfig());

        serverFactory.setUserManager(new WildcardUserManagerFactory().createUserManager());

        CommandFactoryFactory commandFactoryFactory = new CommandFactoryFactory();
        commandFactoryFactory.addCommand("USER", new CustomUSER());

        serverFactory.setCommandFactory(commandFactoryFactory.createCommandFactory());

        BaseUser user = new BaseUser();
        user.setName("anonymous");
        user.setHomeDirectory("/Users/geoffrey/Desktop/ftp/");

        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new WritePermission());

        user.setAuthorities(authorities);


        try {
            serverFactory.getUserManager().save(user);
        } catch (FtpException e) {
            e.printStackTrace();
        }

        ListenerFactory factory = new ListenerFactory();
        factory.setPort(2221);
        serverFactory.addListener("default", factory.createListener());
        FtpServer server = serverFactory.createServer();

        return server;
    }
}
