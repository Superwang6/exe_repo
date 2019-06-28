package com.tedu.sp09.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;

//通过指定服务id可以知道向哪一台主机转发调用
@FeignClient(name = "item-service",fallback = ItemFeignServiceFB.class)
public interface ItemFeignService {
	/**
	 *	Feign 利用spring mvc注解，反向生成访问路径
	 *	根据在mapping中指定的路径，在主机地址后面拼接路径
	 *	根据@PathVariable配置，把参数数据拼接到路径中
	 *	http://localhost:8001/123
	 *	向拼接的路径转发调用 
	 */
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);

	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}
