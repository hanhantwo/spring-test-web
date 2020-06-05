package com.cn.strategy;

import lombok.Data;

/**
 * @ClassName Cat
 * @Description 实现Comparable 类 同时指定泛型类  Comparable<Cat>
 * @Author zhanghongjun
 * @Date 2020-06-05 21:07
 * @Version 1.0
 */
@Data
public class Cat implements Comparable<Cat> {
    int weight , height;
    public Cat(int weight, int height){
        this.weight=weight;
        this.height=height;
    }
    @Override
    public int compareTo(Cat c ){
        if(this.weight<c.weight){
            return -1;
        }else if(this.weight>c.weight){
            return 1;
        }else{
            return  0;
        }
    }

}
