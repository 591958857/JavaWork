package com.alex.springDemo2.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectMapper mapperIgnoreUnknownField = new ObjectMapper();// 忽略不存在的字段.
	private static ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
	
    static {
        mapperIgnoreUnknownField.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
	 * 将对象转成json.
	 * 
	 * @param obj
	 *            对象
	 * @return
	 */
	public static String toFormatJson(Object obj) {
		try {
			if (obj == null) {
				return null;
			}
			else {
				return writer.writeValueAsString(obj);
			}
		}
		catch (Exception e) {
			// LOGGER.info(e.getMessage(), e);
			logger.warn(e.getMessage());
		}
		return null;
	}
    public static String toJson(Object obj) {
        String json = null;
        try {
            if (obj != null) {
                json = mapper.writeValueAsString(obj);
            }
            return json;
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return json;
    }

    public static <T> T toObject(String json, Class<T> clazz, boolean ignoreUnknownField) {
        if (json == null || json.length() == 0) {
            return null;
        }

        try {
            if (ignoreUnknownField) {
                return mapperIgnoreUnknownField.readValue(json, clazz);
            } else {
                return mapper.readValue(json, clazz);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return null;
    }

    public static <T> List<T> toListObject(String content, Class<T> valueType) {
        if (content == null || content.length() == 0) {
            return null;
        }

        try {
            return mapper.readValue(content, mapper.getTypeFactory().constructParametricType(List.class, valueType));
        } catch (Exception e) {
        }

        return null;
    }
    
    public static <T> List<T> toObject(List<String> jsonList, Class<T> valueType) {
		return toObject(jsonList, valueType, false);
	}
    
    public static <T> List<T> toObject(List<String> jsonList, Class<T> valueType, boolean ignoreUnknownField) {
		if (jsonList == null || jsonList.isEmpty()) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (String json : jsonList) {
			list.add(JsonUtil.toObject(json, valueType, ignoreUnknownField));
		}
		return list;
	}
    
    @SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String content) {
		return JsonUtil.toObject(content, Map.class);
	}

	@SuppressWarnings("unchecked")
	public static Set<Object> toSet(String content) {
		return JsonUtil.toObject(content, Set.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> toMap(String json, Class<T> clazz) {
		return JsonUtil.toObject(json, Map.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> Set<T> toSet(String json, Class<T> clazz) {
		return JsonUtil.toObject(json, Set.class);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toNotNullMap(String json) {
		Map<String, Object> map = JsonUtil.toObject(json, Map.class);
		if (map == null) {
			map = new LinkedHashMap<String, Object>();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> toNotNullMap(String json, Class<T> clazz) {
		Map<String, T> map = JsonUtil.toObject(json, Map.class);
		if (map == null) {
			map = new LinkedHashMap<String, T>();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> Set<T> toNotNullSet(String json, Class<T> clazz) {
		Set<T> set = JsonUtil.toObject(json, Set.class);
		if (set == null) {
			set = new LinkedHashSet<T>();
		}
		return set;
	}

	/**
	 * 类型转换.
	 * 
	 * @param obj
	 * @param clazz
	 * @see BeanCopier.copy
	 * @return
	 */
	@Deprecated
	public static <T> T convert(Object obj, Class<T> clazz) {
		String json = JsonUtil.toJson(obj);
		return toObject(json, clazz);
	}

	/**
	 * 将Json转换成对象.
	 * 
	 * @param json
	 * @param valueType
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> clazz) {
		return toObject(json, clazz, false);
	}


}
