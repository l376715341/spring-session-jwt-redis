package com.xinchao.tech.xinchaoad.util;

import com.xinchao.tech.xinchaoad.common.domain.Page;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: han
 * @Date: 2018/6/21 15:53
 * @Description:
 */
public class CommonUtil {

    /**
     * 从request中获得参数Map，并返回可读的Map.
     *
     * @param request the request
     * @return the parameter map
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<String, String[]> properties = request.getParameterMap();
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Set<String> keySet = properties.keySet();
        for (String key : keySet) {
            String[] values = properties.get(key);
            if (Strings.isNotEmpty(values[0])) {
                String value = "";
                for (int i = 0; i < values.length; i++) {
                    if (values[i] != null && !"".equals(values[i])) {
                        value += values[i] + ",";
                    }
                }
                if (value != null && !"".equals(value)) {
                    value = value.substring(0, value.length() - 1);
                }
                returnMap.put(key, value);
            }

        }
        return returnMap;
    }

    /**
     * 通过反射机制获取service实现类 并且封装分页信息
     *
     * @param Map<String, Object> map
     */
    public static void modifyConditions(Map<String, Object> map,String className,Page page) throws Exception{

//        //通过共通方法获取ApplicationContext上下文，然后以反射的方式获取各自Service下方法
//        ApplicationContext context = SpringUtil.getApplicationContext();
//        Class<?>  cls = context.getBean(className).getClass();
//        Method m = cls.getDeclaredMethod(Constants.COUNTALL,Map.class);
//        int countAll = (int) m.invoke(context.getBean(className),map);
//        //注：m.invoke方法第一个参数不能使用newInstance方法，否则Service中dao的注入失败，dao为null
//
//
//        if(null != map.get("pageSize")) {
//            map.put("pageSize", Integer.parseInt( (String) map.get("pageSize") ));
//        } else {
//            map.put("pageSize", Constants.PAGESIZE);
//        }
//        if(null != map.get("pageIndex")) {
//            Integer pageIndex = Integer.parseInt( (String) map.get("pageIndex") );
//            Integer pageSize = (Integer) map.get("pageSize");
//            page.setCurrentPage(pageIndex);
//            if (pageIndex < 1  ||  pageSize < 1) {
//                map.put("pageSize", countAll);
//                map.put("startIndex", 0);
//            } else {
//                int startIndex = ( pageIndex.intValue() - 1) * pageSize.intValue();
//                map.put("startIndex", startIndex);
//            }
//        } else {
//            map.put("pageSize", countAll);
//            map.put("startIndex", 0);
//        }
//
//        int totalPage = countAll/(Integer) map.get("pageSize");
//        int mod = countAll%(Integer) map.get("pageSize");
//        totalPage = (mod > 0) ? (totalPage + 1):totalPage;
//        page.setTotalPage(totalPage);
//        page.setTotalCount(countAll);
    }


}
