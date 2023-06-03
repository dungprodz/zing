package com.kma.zing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZingApplication.class, args);
    }

//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//
//        TblUsersEntity user = new TblUsersEntity();
//        user.setId(1);
//        user.setUsername("hieu");
//        user.setPassword(passwordEncoder.encode("1234"));
//        user.setStatus("ACTIVE");
//        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//        userRepository.save(user);
//        System.out.println(user);
//    }

}
