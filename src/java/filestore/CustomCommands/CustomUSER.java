package filestore.CustomCommands;

import filestore.Configuration;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.ftpserver.command.impl.USER;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.impl.FtpIoSession;
import org.apache.ftpserver.impl.FtpServerContext;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by geoffrey on 01/12/2016.
 */
public class CustomUSER extends USER {
    @Override
    public void execute(FtpIoSession session, FtpServerContext context, FtpRequest request) throws IOException, FtpException {
        String email = request.getArgument();
        boolean valid = isValid(email) && !email.contains("|");

        if(!valid){
            session.write("\nYou must write your email address in place of the password and it should be a valid email.\n");
            return;
        }
        else {
            session.setAttribute("email", email);
            BaseUser user = new BaseUser();
            user.setName(email);
            String homeDir = Configuration.getFolder().getAbsolutePath()+"/"+email+"/";
            user.setHomeDirectory(homeDir);

            List<Authority> auth = new LinkedList<Authority>();
            auth.add(new WritePermission());
            user.setAuthorities(auth);

            context.getUserManager().save(user);

            super.execute(session, context, request);
        }
    }

    private boolean isValid(String email){
        return EmailValidator.getInstance().isValid(email);
    }
}
