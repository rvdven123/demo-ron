package com.rabo.demoron;

import com.rabo.demoron.repos.account.Account;
import com.rabo.demoron.repos.account.AccountRepo;
import com.rabo.demoron.repos.debitcard.DebitCard;
import com.rabo.demoron.repos.debitcard.DebitCardRepo;
import com.rabo.demoron.repos.poa.PowerOfAttorney;
import com.rabo.demoron.repos.poa.PowerOfAttorneyRepo;
import com.rabo.demoron.userinfo.GetUserInfo;
import com.rabo.demoron.userinfo.model.UserInfoVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoRonApplicationTests {

    //power-of-attorney-stub moet draaien op 8080, localhost.
    //TODO Use Repo mocks instead of REST Repo's

    @Autowired
    PowerOfAttorneyRepo powerOfAttorneyRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    GetUserInfo getUserInfo;

    @Autowired
    DebitCardRepo debitCardRepo;

    @Test
    void contextLoads() {
    }

    @Test
    public void testFindAll(){
        List<PowerOfAttorney> attorneys = powerOfAttorneyRepo.findAll();
        assert attorneys.size() == 4;
    }

    @Test
    public void testFindPowerOfAttorneyById(){
        PowerOfAttorney attorney = powerOfAttorneyRepo.findById("0001");
        assert attorney != null;
    }

    @Test
    public void getFindAccountById(){
        Account account = accountRepo.findById("123123123");
        assert account!=null;
    }

    @Test
    public void getUserInfo(){
        UserInfoVO userInfo = getUserInfo.execute("Fellowship of the ring");
        assert userInfo.getAccounts() != null;
        assert userInfo.getDebitCards() != null && userInfo.getDebitCards().size()==2;

    }

    @Test
    public void getFindDebitCardById(){
        DebitCard debitCard = debitCardRepo.findById("1111");
        assert debitCard != null;
    }

}
