package com.robin.encuentas;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfigurations {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
