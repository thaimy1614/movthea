package com.example.demo.service.impl;

import com.example.demo.model.request.LoginRequest;
import com.example.demo.model.response.LoginResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

//    @Override
//    public LoginResponse login(LoginRequest loginRequest) {
//        LoginResponse loginResponse = new LoginResponse();
//        Optional<UserEntity> userEntity =
//                userRepository.findAllByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
//        if (!userEntity.isPresent()) {
//            throw new LoginFalseException("username or password incorrect");
//        } else {
//            String token = "test123";
//            loginResponse.setToken(token);
//            loginResponse.setRole(userEntity.get().getUserRole();
//            loginResponse.setName(loginRequest.getUsername());
//            loginResponse.setUserId(userEntity.get().getId());
//            return loginResponse;
//        }
//    }

//    private String getJWTToken(String username) {
//        String secretKey = "mySecretKey";
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("ROLE_USER");
//
//        String token = Jwts
//                .builder()
//                .setId("softtekJWT")
//                .setSubject(username)
//                .claim("authorities",
//                        grantedAuthorities.stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .collect(Collectors.toList()))
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 600000))
//                .signWith(SignatureAlgorithm.HS512,
//                        secretKey.getBytes()).compact();
//
//        return "Bearer " + token;
//    }
}
