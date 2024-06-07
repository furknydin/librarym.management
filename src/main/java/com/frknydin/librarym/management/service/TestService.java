package com.frknydin.librarym.management.service;


import com.frknydin.librarym.management.model.request.GetTestRequest;
import com.frknydin.librarym.management.model.response.GetTestResponse;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public GetTestResponse testService(GetTestRequest getTestRequest){
        return new GetTestResponse(getTestRequest.getName(),getTestRequest.getId());
    }
}
