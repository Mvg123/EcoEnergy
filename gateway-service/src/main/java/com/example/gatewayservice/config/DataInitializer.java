package com.example.gatewayservice.config;

import com.example.gatewayservice.entity.CompanyType;
import com.example.gatewayservice.entity.User;
import com.example.gatewayservice.repository.UserRepository;
import com.example.gatewayservice.util.Sha512Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {

        if (userRepository.count() == 0) {

            // USER 1 (BUYER)
            String salt1 = Sha512Util.generateSalt();
            User user1 = new User();
            user1.setSellComId("user1");
            user1.setSellComPw(Sha512Util.hash("user123", salt1)); // 초기 비밀번호: user123
            user1.setSalt(salt1);
            user1.setSellComName("에너지구매처1");
            user1.setSellRegNum("1000000001");
            user1.setSellRepName("구매자1");
            user1.setSellComBirth("19900101");
            user1.setSellComAdr("서울시 강남구");
            user1.setSellComNum("02-1111-0001");
            user1.setSellBmName("구매담당1");
            user1.setSellBmNum("010-1000-0001");
            user1.setSellBmDep("전략기획팀");
            user1.setSellComEmail("user1@buyer.com");
            user1.setRole("USER");
            user1.setCompanyType(CompanyType.BUYER);
            user1.setApprovYn("Y");
            user1.setMarketingCheck("Y");
            userRepository.save(user1);

// USER 2 (BUYER)
            String salt2 = Sha512Util.generateSalt();
            User user2 = new User();
            user2.setSellComId("user2");
            user2.setSellComPw(Sha512Util.hash("user123", salt2));
            user2.setSalt(salt2);
            user2.setSellComName("에너지구매처2");
            user2.setSellRegNum("1000000002");
            user2.setSellRepName("구매자2");
            user2.setSellComBirth("19900202");
            user2.setSellComAdr("서울시 서초구");
            user2.setSellComNum("02-1111-0002");
            user2.setSellBmName("구매담당2");
            user2.setSellBmNum("010-1000-0002");
            user2.setSellBmDep("운영지원팀");
            user2.setSellComEmail("user2@buyer.com");
            user2.setRole("USER");
            user2.setCompanyType(CompanyType.BUYER);
            user2.setApprovYn("Y");
            user2.setMarketingCheck("Y");
            userRepository.save(user2);

// USER 3 (BUYER)
            String salt3 = Sha512Util.generateSalt();
            User user3 = new User();
            user3.setSellComId("user3");
            user3.setSellComPw(Sha512Util.hash("user123", salt3));
            user3.setSalt(salt3);
            user3.setSellComName("에너지구매처3");
            user3.setSellRegNum("1000000003");
            user3.setSellRepName("구매자3");
            user3.setSellComBirth("19900303");
            user3.setSellComAdr("서울시 송파구");
            user3.setSellComNum("02-1111-0003");
            user3.setSellBmName("구매담당3");
            user3.setSellBmNum("010-1000-0003");
            user3.setSellBmDep("자재관리팀");
            user3.setSellComEmail("user3@buyer.com");
            user3.setRole("USER");
            user3.setCompanyType(CompanyType.BUYER);
            user3.setApprovYn("Y");
            user3.setMarketingCheck("Y");
            userRepository.save(user3);

            // USER 4 (SELLER)
            String salt4 = Sha512Util.generateSalt();
            User user4 = new User();
            user4.setSellComId("user4");
            user4.setSellComPw(Sha512Util.hash("user123", salt4));
            user4.setSalt(salt4);
            user4.setSellComName("태양광발전소4");
            user4.setSellRegNum("2000000004");
            user4.setSellRepName("판매자4");
            user4.setSellComBirth("19850404");
            user4.setSellComAdr("경기도 수원시");
            user4.setSellComNum("031-444-0004");
            user4.setSellBmName("판매담당4");
            user4.setSellBmNum("010-2000-0004");
            user4.setSellBmDep("기술지원팀");
            user4.setSellComEmail("user4@seller.com");
            user4.setRole("USER");
            user4.setCompanyType(CompanyType.SELLER);
            user4.setApprovYn("Y");
            user4.setMarketingCheck("Y");
            userRepository.save(user4);

// USER 5 (SELLER)
            String salt5 = Sha512Util.generateSalt();
            User user5 = new User();
            user5.setSellComId("user5");
            user5.setSellComPw(Sha512Util.hash("user123", salt5));
            user5.setSalt(salt5);
            user5.setSellComName("풍력발전소5");
            user5.setSellRegNum("2000000005");
            user5.setSellRepName("판매자5");
            user5.setSellComBirth("19850505");
            user5.setSellComAdr("강원도 춘천시");
            user5.setSellComNum("033-555-0005");
            user5.setSellBmName("판매담당5");
            user5.setSellBmNum("010-2000-0005");
            user5.setSellBmDep("발전운영팀");
            user5.setSellComEmail("user5@seller.com");
            user5.setRole("USER");
            user5.setCompanyType(CompanyType.SELLER);
            user5.setApprovYn("Y");
            user5.setMarketingCheck("Y");
            userRepository.save(user5);

// USER 6 (SELLER)
            String salt6 = Sha512Util.generateSalt();
            User user6 = new User();
            user6.setSellComId("user6");
            user6.setSellComPw(Sha512Util.hash("user123", salt6));
            user6.setSalt(salt6);
            user6.setSellComName("바이오에너지6");
            user6.setSellRegNum("2000000006");
            user6.setSellRepName("판매자6");
            user6.setSellComBirth("19850606");
            user6.setSellComAdr("충청남도 천안시");
            user6.setSellComNum("041-666-0006");
            user6.setSellBmName("판매담당6");
            user6.setSellBmNum("010-2000-0006");
            user6.setSellBmDep("에너지사업부");
            user6.setSellComEmail("user6@seller.com");
            user6.setRole("USER");
            user6.setCompanyType(CompanyType.SELLER);
            user6.setApprovYn("Y");
            user6.setMarketingCheck("Y");
            userRepository.save(user6);
        }
    }
}
