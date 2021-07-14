package com.cn.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.thread.MyCallable;
import com.cn.thread.MyRnunable;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ThreadController
 * @Description TODO 书写关于线程相关的案例
 * @Author
 * @Date 2021/3/2 11:27
 */
@Slf4j
@RestController
@RequestMapping("/thread")
public class ThreadController {
	/**
	 * 测试线程池调用Runnable
	 */
	@RequestMapping("/testRunnable")
	@ApiOperation(value = "查询api网关分组") // swagger注解
	@ApiImplicitParams({
			@ApiImplicitParam(name = "testRunnable", value = "api分组id", required = true, paramType = "path", dataType = "Long") })
	public void testRunnable() {
//自定义一个线程池
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, // corePoolSize 线程池核心线程大小
				5, // maximumPoolSize 线程池最大线程数量
				60, // keepAliveTime 空闲线程存活时间
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3), // 工作队列
				Executors.defaultThreadFactory(), // threadFactory 线程工厂
				new ThreadPoolExecutor.AbortPolicy());
		// 拒绝策略有四种
		// 1：CallerRunsPolicy 该策略下，在调用者线程中直接执行被拒绝任务的run方法，除非线程池已经shutdown，则直接抛弃任务。
		// 2：AbortPolicy 直接丢弃任务，并抛出RejectedExecutionException异常。
		// 3：DiscardPolicy 该策略下，直接丢弃任务，什么都不做
		// 4：DiscardOldestPolicy 该策略下，抛弃进入队列最早的那个任务，然后尝试把这次拒绝的任务放入队列

		// 测试书写哈希算法求& （利用hashMap 的hash碰撞算法）后，多线程执行集合数据

		Map<Integer, String> map1 = new HashMap<>();
		Map<Integer, String> map2 = new HashMap<>();
		Map<Integer, String> map3 = new HashMap<>();

		// 创建三个数组

		int[] a1 = { 0, 1 };
		int[] a2 = { 2, 3 };
		int[] a3 = { 4, 5 };

		for (int i = 0; i < 100; i++) {
			String str = "当前i的值：" + i + ",  " + "当前i的hashCode ：" + Integer.valueOf(i).hashCode();
			System.out.println("当前i的值：" + i);
			System.out.println("当前i的hashCode ：" + Integer.valueOf(i).hashCode());
			if (Arrays.asList(a1).contains(Integer.valueOf(i).hashCode() & 5)) {
				map1.put(i, str);
			} else if (Arrays.asList(a2).contains(Integer.valueOf(i).hashCode() & 5)) {
				map2.put(i, str);
			} else if (Arrays.asList(a3).contains(Integer.valueOf(i).hashCode() & 5)) {
				map3.put(i, str);
			}
		}

		Runnable runnable1 = new MyRnunable(map1);
		Runnable runnable2 = new MyRnunable(map2);
		Runnable runnable3 = new MyRnunable(map3);
		// todo: 可以有两种方式执行Runnable，但是Callable只能是submit
		poolExecutor.submit(runnable1);
		poolExecutor.execute(runnable1);
	}

	/**
	 * 测试线程池调用Callable返回值
	 */

	@RequestMapping("/testCallable")
	public void testCallable() {
		// 自定义一个线程池
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, // corePoolSize 线程池核心线程大小
				5, // maximumPoolSize 线程池最大线程数量
				60, // keepAliveTime 空闲线程存活时间
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3), // 工作队列
				Executors.defaultThreadFactory(), // threadFactory 线程工厂
				new ThreadPoolExecutor.AbortPolicy());
		// 拒绝策略有四种
		// 1：CallerRunsPolicy 该策略下，在调用者线程中直接执行被拒绝任务的run方法，除非线程池已经shutdown，则直接抛弃任务。
		// 2：AbortPolicy 直接丢弃任务，并抛出RejectedExecutionException异常。
		// 3：DiscardPolicy 该策略下，直接丢弃任务，什么都不做
		// 4：DiscardOldestPolicy 该策略下，抛弃进入队列最早的那个任务，然后尝试把这次拒绝的任务放入队列

		// 测试书写哈希算法求& （利用hashMap 的hash碰撞算法）后，多线程执行集合数据

		Map<Integer, String> map1 = new HashMap<>();
		Map<Integer, String> map2 = new HashMap<>();
		Map<Integer, String> map3 = new HashMap<>();

		// 创建三个数组

		int[] a1 = { 0, 1 };
		int[] a2 = { 2, 3 };
		int[] a3 = { 4, 5 };

		for (int i = 0; i < 100; i++) {
			String str = "当前i的值：" + i + ",  " + "当前i的hashCode ：" + Integer.valueOf(i).hashCode();
			System.out.println("当前i的值：" + i);
			System.out.println("当前i的hashCode ：" + Integer.valueOf(i).hashCode());
			if (Arrays.asList(a1).contains(Integer.valueOf(i).hashCode() & 5)) {
				map1.put(i, str);
			} else if (Arrays.asList(a2).contains(Integer.valueOf(i).hashCode() & 5)) {
				map2.put(i, str);
			} else if (Arrays.asList(a3).contains(Integer.valueOf(i).hashCode() & 5)) {
				map3.put(i, str);
			}
		}

		// new 三个线程对象
		Callable callable1 = new MyCallable(map1);
		Callable callable2 = new MyCallable(map2);
		Callable callable3 = new MyCallable(map3);

		// todo : 记住执行callable对象是FutureTask
		FutureTask<Object> objectFutureTask1 = new FutureTask<Object>(callable1);
		// 线程池执行objectFutureTask1
		try {
			poolExecutor.submit(objectFutureTask1);
			// 获取返回值
			objectFutureTask1.get();
		} catch (InterruptedException e) {
			log.error("获取返回值失败：{}", e);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		// 同理 callable2 callable3 也是如此

	}
}
