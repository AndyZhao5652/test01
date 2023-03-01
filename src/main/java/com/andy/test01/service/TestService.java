package com.andy.test01.service;

import com.andy.test01.domain.Test;
import com.andy.test01.mapper.TestMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

//import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.selectByExample(null);
    }
}
