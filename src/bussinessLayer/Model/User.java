package bussinessLayer.Model;

public class User {
    private String username;
    private String password;
    private PaymentMethod paymentMethod;


    public User(String username, String password, String paymentMethod) {
        this.username = username;
        this.password = password;
        this.paymentMethod = new PaymentMethod(paymentMethod);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
