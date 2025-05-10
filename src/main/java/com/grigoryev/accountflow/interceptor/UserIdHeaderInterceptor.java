package com.grigoryev.accountflow.interceptor;

import com.grigoryev.accountflow.exception.UserIdHeaderNotValidException;
import com.grigoryev.accountflow.exception.UserNotFoundException;
import com.grigoryev.accountflow.model.User;
import com.grigoryev.accountflow.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class UserIdHeaderInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;
    private final UserHolder userHolder;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        if (!"GET".equalsIgnoreCase(request.getMethod())) {
            Long userId;
            try {
                String userIdHeader = request.getHeader("USER_ID");
                if (StringUtils.isBlank(userIdHeader)) {
                    throw new UserIdHeaderNotValidException("USER_ID is null or blank");
                }
                userId = Long.parseLong(userIdHeader);
            } catch (NumberFormatException e) {
                throw new UserIdHeaderNotValidException("USER_ID is not a valid number");
            }

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User with id = %d is not found".formatted(userId)));
            userHolder.setUser(user);
        }
        return true;
    }

}
