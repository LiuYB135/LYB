package com.Demo.dao;

import com.Demo.pojo.TestPojo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestMapper {
    int insert(TestPojo record);
    List<TestPojo> select();
}