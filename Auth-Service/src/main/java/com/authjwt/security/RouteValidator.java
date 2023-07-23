package com.authjwt.security;

import com.authjwt.dto.RequestDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-path")
public class RouteValidator {

   private List<RequestDto> paths;

    public List<RequestDto> getPahts(){
        return paths;
    }

    public void setPaths(List<RequestDto> paths){
        this.paths =paths;
    }

    public boolean isAdminCheck(RequestDto requestDto){

        return paths.stream().anyMatch(p -> Pattern.matches(p.getUri(),requestDto.getUri()) && p.getMethod().equals(requestDto.getMethod()));

    }


}
