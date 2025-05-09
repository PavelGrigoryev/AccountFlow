package com.grigoryev.accountflow.mapper;

import com.grigoryev.accountflow.dto.phone.PhoneDataRequest;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;
import com.grigoryev.accountflow.model.PhoneData;
import com.grigoryev.accountflow.model.User;
import org.springframework.stereotype.Component;

@Component
public class PhoneDataMapper {

    public PhoneData toPhoneData(PhoneDataRequest request, User user) {
        return new PhoneData().setPhone(request.phone())
                .setUser(user);
    }

    public PhoneDataResponse toPhoneDataResponse(PhoneData phoneData) {
        return new PhoneDataResponse(phoneData.getId(), phoneData.getPhone());
    }

}
