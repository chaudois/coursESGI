/**
 * Created by damie on 03/05/2017.
 */
public class Account {
    private double money;
    private boolean authorized;



    public double getMoney() {
        return money;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public void setMoney(double money) {
        this.money += money;
    }
}
