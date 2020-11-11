package com.cn.strategy;

import lombok.Data;

/**
 * @ClassName Dog
 * @Description  实现Comparable 类 同时指定泛型类  Comparable<Cat>
 * @Author zhanghongjun
 * @Date 2020-06-05 21:23
 * @Version 1.0
 */
public class Dog implements Comparable<Dog>{
    int food;
   public Dog(int food){
       this.food = food;
   }
    @Override
    public int compareTo(Dog c) {
        if(this.food<c.food){
            return -1;
        }else if(this.food>c.food){
            return 1;
        }else{
            return  0;
        }
    }
}
