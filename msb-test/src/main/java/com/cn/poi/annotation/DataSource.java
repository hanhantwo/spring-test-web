package com.cn.poi.annotation;

import java.lang.annotation.*;

import com.cn.poi.DataSourceType;

/**
 * 自定义多数据源切换注解
 *
 * 优先级：先方法，后类，如果方法覆盖了类上的数据源类型，以方法的为准，否则以类上的为准
 *
 * @author xtsz XuY&SongJ
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
	/**
	 * 切换数据源名称
	 */
	public DataSourceType value() default DataSourceType.MASTER;
}
