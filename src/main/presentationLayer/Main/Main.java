package main.presentationLayer.Main;

import main.bussinessLayer.Controller.Controller;
import main.databaseLayer.DAO.DAOUser;

public class Main {
    public static void main(String[] args) throws Exception {
        //Controller controller = new Controller();
        DAOUser daoUser = new DAOUser();
        daoUser.borrowBook("info","test");
        daoUser.returnBook("info","test");
    }
}
