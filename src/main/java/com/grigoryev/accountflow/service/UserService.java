package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.user.UserSearchRequest;
import com.grigoryev.accountflow.dto.user.UserSearchResponse;
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
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
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

}
