package com.grigoryev.accountflow.repository.spec;

import com.grigoryev.accountflow.dto.user.UserSearchRequest;
import com.grigoryev.accountflow.model.EmailData;
import com.grigoryev.accountflow.model.PhoneData;
import com.grigoryev.accountflow.model.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class UserSpecification {

    public Specification<User> searchUsers(UserSearchRequest request) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (request.dateOfBirth() != null) {
                predicate = cb.and(predicate, cb.greaterThan(root.get("dateOfBirth"), request.dateOfBirth()));
            }

            if (StringUtils.isNotBlank(request.phone())) {
                Join<User, PhoneData> phoneJoin = root.join("phones");
                predicate = cb.and(predicate, cb.equal(phoneJoin.get("phone"), request.phone()));
            }

            if (StringUtils.isNotBlank(request.name())) {
                predicate = cb.and(predicate, cb.like(root.get("name"), request.name() + "%"));
            }

            if (StringUtils.isNotBlank(request.email())) {
                Join<User, EmailData> emailJoin = root.join("emails");
                predicate = cb.and(predicate, cb.equal(emailJoin.get("email"), request.email()));
            }

            query.distinct(true);
            return predicate;
        };
    }

}
