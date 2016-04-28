/**
 * <br>项目名: csvo
 * <br>文件名: Singleton.java
 * <br>Copyright 2016 北京壹平台科技有限公司
 */
package com.id.tools;

/** 
 * <br>类 名: Singleton 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年4月27日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
public class Singleton {

    //一个静态的实例
    private static Singleton singleton;
    //私有化构造函数
    private Singleton(){}
    //给出一个公共的静态方法返回一个单一实例
    public static Singleton getInstance(){
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
