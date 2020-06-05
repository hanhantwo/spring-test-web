package com.cn.strategy;

import java.util.Arrays;

/**
 * @ClassName Main
 * @Description 策略模式
 * 策略模式就是用的
 * Comparator 和 Comparable 两个接口
 * @Author zhanghongjun
 * @Date 2020-06-05 20:53
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
       Dog[] dd ={new Dog(100),new Dog(85),new Dog(96),new Dog(45)};
        Sorter<Dog> sorter = new Sorter();
        DogCompartor dogCompartor = new DogCompartor();
        /**
         * 添加比较策略方式，这就是策略模式的好处,狗目前只有一种比较策略
         */
        sorter.sort(dd,dogCompartor);

        Cat[] cat = {new Cat(13,34),new Cat(11,45),new Cat(32,78),new Cat(21,4)};
        CatWeightCompartor c = new CatWeightCompartor();
        Sorter<Cat> sorter1 = new Sorter();
        sorter1.sort(cat,c);
        System.out.println(Arrays.toString(cat));

        /**
         * 函数式方法
         */
        sorter1.sort(cat,(o1,o2)->{
            if(o1.height<o2.height){
                return -1;
            }else if(o1.height>o2.height){
                return 1;
            }else{
                return  0;
            }
        });
        System.out.println(Arrays.toString(cat));
    }
}
