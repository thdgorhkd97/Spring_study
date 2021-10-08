package com.example.study.service;

import com.example.study.Ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1. request data
    // 2. user 생성
    // 3. 생성된 data를 기준으로 UserApiResponse를 return하는 것

   @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

       // request data
       UserApiRequest userApiRequest = request.getData();

       // user 생성
       User user = User.builder()
               .account(userApiRequest.getAccount())
               .password(userApiRequest.getPassword())
               .status(UserStatus.REGISTERED)
               .phoneNumber(userApiRequest.getPhoneNumber())
               .email(userApiRequest.getEmail())
               .registeredAt(LocalDateTime.now())
               .build();

       User newUser = userRepository.save(user);

       // 생성된 data를 기준으로 userApiResponse를 return


        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

      return userRepository.findById(id)
                .map(user->response(user))
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

       // data 가져오고
        UserApiRequest userApiRequest = request.getData();
        // id로 user 데이터 찾아서
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user ->{
            // data를 update 하고
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());

            return user;

        })
                .map(user -> userRepository.save(user)) // update
                .map(updateUser -> response(updateUser)) // userApiResponse해서 저장
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

       // id로 찾아서
       Optional<User> optional = userRepository.findById(id);

           // delete 하고
           return optional.map(user -> {
               userRepository.delete(user);

               // response 반환
               return Header.OK();
           }).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    private Header<UserApiResponse> response(User user){
       // user -> userApiResponse 객체를 리턴하자
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // 암호화 필요
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data 로 리턴
        return Header.OK(userApiResponse);
    }
}
