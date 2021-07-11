package org.example.domain.spaceinvaders.core.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class RadarResponse {

    private Map<String,Integer> invaderCount;

    @Override
    public String toString() {
        if (invaderCount.isEmpty()){
            return "No invaders signature found in radar";
        }else{
            return invaderCount
                    .entrySet()
                    .stream()
                    .map( entry -> "Invader "+entry.getKey() + "occured "+ entry.getValue()+ " times")
                    .collect(Collectors.joining(","));
        }

    }
}
