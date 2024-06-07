package com.frknydin.librarym.management.service;


import com.frknydin.librarym.management.request.GetTestRequest;
import com.frknydin.librarym.management.response.GetTestResponse;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public GetTestResponse testService(GetTestRequest getTestRequest){
        GetTestResponse testResponse = new GetTestResponse(getTestRequest.getName(),getTestRequest.getId());
        return testResponse;
    }
}
