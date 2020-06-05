package com.cn.strategy;

import java.util.Comparator;

/**
 * @ClassName DogCompartor
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-05 21:49
 * @Version 1.0
 */
public class DogCompartor  implements Comparator<Dog>{
    @Override
    public int compare(Dog o1, Dog o2) {
        if(o1.food<o2.food){
            return -1;
        }else if(o1.food>o2.food){
            return 1;
        }else{
            return  0;
        }
    }
}
