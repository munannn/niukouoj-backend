package com.luo.niukouoj.judge.codesandbox;

/**
 * @author 木南
 * @version 1.0
 * @Description 代码沙箱工厂
 */

import com.luo.niukouoj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.luo.niukouoj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.luo.niukouoj.judge.codesandbox.impl.ThirdPartCodeSandBox;
import com.luo.niukouoj.model.enums.CodeSandBoxEnum;

/**
 * 使用单例模式 + 工厂模式 ，确保只有一个工厂实例，工厂通过传入的字符串参数创建对应的代码沙箱
 */
public class CodeSandBoxFactory {

    private static CodeSandBoxFactory instance;

    private CodeSandBoxFactory() {

    }

    public static CodeSandBoxFactory getInstance() {
        /*
          使用双重检查锁定来确保线程安全
          1. 首先判断 instance 是否为null，如果不为null，则说明工厂实例已经创建了，直接返回该实例；
             如果为null，则进入同步块。
          2. 使用同步块，确保在多线程环境下只有一个线程能够进入，防止多个线程同时创建实例
          3. 进入同步块后再次检查 instance 是否为null，如果不为null，说明已经有其他线程先一步创建了实例；
             如果此时仍为null，确定没有实例被创建，则创建一个实例，并返回该单例实例
         */
        if (instance == null) {
            synchronized (CodeSandBoxFactory.class) {
                if (instance == null) {
                    instance = new CodeSandBoxFactory();
                }
            }
        }
        return instance;
    }
    
    public CodeSandBox newInstance(String type) {
        CodeSandBoxEnum enumByValue = CodeSandBoxEnum.getEnumByValue(type);
        if (enumByValue == null) {
            return new ExampleCodeSandBox();
        }
        switch (enumByValue) {
            case REMOTE:
                return new RemoteCodeSandBox();
            case THIRD_PART:
                return new ThirdPartCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}
