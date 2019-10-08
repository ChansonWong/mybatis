package com.superatom;

import com.superatom.bean.TimeCity;
import com.superatom.dao.TimeCityMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisMain {

  public static void main(String[] args) throws IOException {
    String resource = "com/superatom/conf/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
    try(SqlSession session = factory.openSession()) {
      // 动态代理TimeCityMapper
      TimeCityMapper mapper = session.getMapper(TimeCityMapper.class);
      TimeCity timeCity = mapper.selectTimeCity("001e8f6de19d4a6b8b1ba5b89ef6fd54", "Shiyan");
      System.out.println(timeCity);
    }
  }
}
