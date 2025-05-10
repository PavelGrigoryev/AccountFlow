package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.login.LoginEmailRequest;
import com.grigoryev.accountflow.dto.login.LoginPhoneRequest;
import com.grigoryev.accountflow.dto.login.LoginResponse;
import com.grigoryev.accountflow.dto.user.UserSearchRequest;
import com.grigoryev.accountflow.dto.user.UserSearchResponse;
import com.grigoryev.accountflow.exception.InvalidPasswordException;
import com.grigoryev.accountflow.exception.UserNotFoundException;
import com.grigoryev.accountflow.mapper.UserMapper;
import com.grigoryev.accountflow.model.User;
import com.grigoryev.accountflow.repository.UserRepository;
import com.grigoryev.accountflow.repository.spec.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public List<UserSearchResponse> searchUsers(UserSearchRequest request) {
        Pageable pageable = PageRequest.of(
                request.page() == null ? 0 : request.page(),
                request.size() == null ? 10 : request.size());
        Specification<User> spec = UserSpecification.searchUsers(request);
        return userRepository.findAll(spec, pageable)
                .map(userMapper::toUserSearchResponse)
                .stream()
                .toList();
    }

    public LoginResponse loginByPhone(LoginPhoneRequest request) {
        User user = userRepository.findByPhone(request.phone())
                .orElseThrow(() -> new UserNotFoundException("User with phone = %s is not found".formatted(request.phone())));
        if (!request.password().equals(user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        return new LoginResponse(jwtService.generateToken(user.getId()));
    }

    public LoginResponse loginByEmail(LoginEmailRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException("User with email = %s is not found".formatted(request.email())));
        if (!request.password().equals(user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        return new LoginResponse(jwtService.generateToken(user.getId()));
    }

}
