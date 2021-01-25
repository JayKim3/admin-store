//package com.study.adminstore.repository;
//
//import com.study.adminstore.AdminStoreApplicationTests;
//import com.study.adminstore.model.entity.User;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//
//public class UserRepositoryMysqlTest extends AdminStoreApplicationTests {
//
//    @Autowired
//    private UserRepositoryMysql userRepositoryMysql;
//
//    @Test
//    @Transactional
//    public void create() {
//         final User user = User.builder()
//                .account("ksj01")
//                .password("ksj01")
//                .status("REGISTERED")
//                .email("ragamuffin6701@gmail.com")
//                .phoneNumber("010-3574-0001")
//                .registeredAt(LocalDateTime.now())
//                .unregisteredAt(LocalDateTime.now())
//                .createdAt(LocalDateTime.now())
//                .createdBy("AdminServer")
//                .updatedAt(LocalDateTime.now())
//                .updatedBy("AdminServer")
//                .build();
//
//         final User newUser = userRepositoryMysql.create(user);
//
//         assertThat(newUser).isNotNull();
//    }
//
//    @Test
//    public void read() {
//        final User user = userRepositoryMysql.findById(3L);
//        System.out.println(user);
//        assertThat(user).isNotNull();
//    }
//
//    @Test
//    public void update() {
////        User user = User.builder()
////                .id(1000L)
////                .account("update_ksj01")
////                .password("update_ksj01")
////                .status("UNREGISTERED")
////                .email("ragamuffin6701up@gmail.com")
////                .phoneNumber("010-3574-0002")
////                .registeredAt(LocalDateTime.now())
////                .unregisteredAt(LocalDateTime.now())
////                .createdAt(LocalDateTime.now())
////                .createdBy("AdminServer")
////                .updatedAt(LocalDateTime.now())
////                .updatedBy("AdminServer")
////                .build();
//
//        final Optional<User> user = Optional.ofNullable(userRepositoryMysql.findById(1000L));
//        user.ifPresent(selectUser->{
//            selectUser.builder()
//                    .account("update_ksj01")
//                    .password("update_ksj01")
//                    .status("UNREGISTERED")
//                    .email("ragamuffin6701up@gmail.com")
//                    .phoneNumber("010-3574-0002")
//                    .registeredAt(LocalDateTime.now())
//                    .unregisteredAt(LocalDateTime.now())
//                    .createdAt(LocalDateTime.now())
//                    .createdBy("AdminServer")
//                    .updatedAt(LocalDateTime.now())
//                    .updatedBy("AdminServer")
//                    .build();
//
//            userRepositoryMysql.update(selectUser);
//        });
//    }
//
//    @Test
//    public void deleteById() {
//        final Optional<User> user = Optional.ofNullable(userRepositoryMysql.findById(1004L));
//        assertThat(user).isPresent();
//
//        user.ifPresent(selectUser->{
//            userRepositoryMysql.deleteById(selectUser.getId());
//        });
//
//        final Optional<User> deleteUser = Optional.ofNullable(userRepositoryMysql.findById(1004L));
//        assertThat(deleteUser).isPresent();
//    }
//
//    @Test
//    public void userCountAll() {
//        int userCount = userRepositoryMysql.userCountAll();
//        System.out.println(userCount);
//        assertThat(userCount).isNotNull();
//    }
//}
//
