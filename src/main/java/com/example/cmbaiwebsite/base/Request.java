package com.example.cmbaiwebsite.base;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.Serializable;

@Data
public class Request<Req> implements Serializable {


    private String testHeader1;

}
