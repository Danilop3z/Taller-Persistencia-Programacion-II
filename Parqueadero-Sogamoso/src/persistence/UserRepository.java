package persistence;

import model.User;
import interfaces.IActionsFile;
import enums.ETypeFile;
import constants.CommonConstants;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class UserRepository extends FilePlain implements IActionsFile {

    private List<User> listUsers;

    public UserRepository() {
        this.listUsers = new ArrayList<User>();
    }

    public Boolean loginCheck(String userName, String pass) {
        for (User u : listUsers) {
            if (u.getUserName().equals(userName) && u.getPassword()
                    .equals(pass)) {
                return true;
            }
        }
        return false;
    }

    public Boolean addUser(User user) {
        if (Objects.isNull(this.findUserByUsername(user.getUserName()))) {
            this.listUsers.add(user);
            return true;
        }
        return false;
    }

    private User findUserByUsername(String userName) {
        for (User user : this.listUsers) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void loadFile(ETypeFile eTypeFile) {
        if(eTypeFile.equals(ETypeFile.SER)) {
            this.loadFileSerializate();
        }
    }

    @Override
    public void dumpFile(ETypeFile eTypeFile) {
        if(eTypeFile.equals(ETypeFile.SER)) {
            this.dumpFileSerializate();
        }
    }


    private void dumpFileSerializate() {
        try (FileOutputStream fileOut = new FileOutputStream(
                this.config.getPathFiles()
                        .concat(this.config.getNameFileSer()));
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this.listUsers);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private void loadFileSerializate() {
        try (FileInputStream fileIn = new FileInputStream(
                this.config.getPathFiles()
                        .concat(this.config.getNameFileSer()));
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            this.listUsers = (List<User>) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }
    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListContacts(List<User> listUsers) {
        this.listUsers = listUsers;
    }

}


