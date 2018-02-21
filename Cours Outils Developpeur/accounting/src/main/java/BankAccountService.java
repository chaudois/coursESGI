/**
 * Created by damie on 03/05/2017.
 */
public class BankAccountService {

    public void updateMoney(Account account,int amount) throws IllegalArgumentException,CreditNotAuthorizedException{
        if(account==null)throw new IllegalArgumentException();
        if(account.getMoney()+amount<0) throw new CreditNotAuthorizedException();
        account.setMoney((double)amount);
    }
}
