package a2.demo.obs;

public class NotifyUser implements Observer {

    @Override
    public void update() {
        System.out.println("Book available");
    }
}
