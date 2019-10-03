/**
 *    Copyright 2009-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.binding;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSession;

/**
 * @author Lasse Voss
 */
public class MapperProxyFactory<T> {

  private final Class<T> mapperInterface;
  private final Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<>();

  public MapperProxyFactory(Class<T> mapperInterface) {
    this.mapperInterface = mapperInterface;
  }

  public Class<T> getMapperInterface() {
    return mapperInterface;
  }

  public Map<Method, MapperMethod> getMethodCache() {
    return methodCache;
  }

  /**
   * JDK动态代理的实现设置为protected表示只能在本类调用
   * @param mapperProxy
   * @return
   */
  @SuppressWarnings("unchecked")
  protected T newInstance(MapperProxy<T> mapperProxy) {
    // JDK动态代理的实现
    /**
     * 三个参数说明：
     * ClassLoader loader 指向当前目标对象（自然是你定义的Mapper接口）使用的类加载器，写法固定
     * Class<?>[] interfaces 目标对象实现的接口的类型，写法固定
     * InvocationHandler h 事件处理接口，需传入一个实现类，一般直接使用匿名内部类（这里就是增强实现）
     */
    return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
  }

  public T newInstance(SqlSession sqlSession) {
    /**
     * MapperProxy就是增强实现类，也是Mapper接口的代理
     * 代理的实现逻辑是，执行代理的invoke方法，以下例子说明：
     * DynamicStarHandler handler = new DynamicStarHandler(new RealStar());
     * Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
     *                 new Class[]{Star.class}, handler);
     * proxy.sign();
     *
     * 以上的代码，proxy调用sign，其实就是调用了代理类DynamicStarHandler的invoke方法。
     *
     * 所有可以重点看MapperProxy的invoke方法！！！
     */
    final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface, methodCache);
    return newInstance(mapperProxy);
  }

}
