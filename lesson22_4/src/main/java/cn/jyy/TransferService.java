package cn.jyy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Autowired
    private TransferDao transferDao;

    public int transfer(String userFrom, String userTo, int money) {
        try {
            int i = transferDao.updateAccountMoney(userFrom, userTo, money);
            return i;
        } catch (Exception e) {
            System.out.println("AccountService.transfer发生异常：原因" + e.getMessage());
            return 4;
        }

    }
    public void add() {
        //省略一些逻辑
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //省略一些逻辑
    }
}
