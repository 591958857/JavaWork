package com.alex.springDemo2.controller;

import java.util.HashMap;
import java.util.Map;

import com.alex.springDemo2.util.JsonUtil;


public class BaseAdminController {
	protected Map<String,Object> wrapperMap(Integer status,String message ,Object data){
		Map<String,Object> _return = new HashMap<String, Object>();
		_return.put("status", status);
		_return.put("msg", message);
		if(null != data){
			_return.put("data", data);
		}
		return _return;
	}
	
	protected String AjaxResult(Integer status,String message ,Object data){
		return JsonUtil.toJson(this.wrapperMap(status, message, data));
	}
	
}
