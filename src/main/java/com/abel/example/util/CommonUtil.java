package com.abel.example.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.abel.example.bean.MatterHandlerVO;
import com.abel.example.bean.ModelBO;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

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
        //返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Set<String> keySet = properties.keySet();
        for (String key : keySet) {
            String[] values = properties.get(key);
            String value = "";
            if (values != null && (values.length == 1 && StringUtils.isNotBlank(values[0])) ? true : false) {
                for (int i = 0; i < values.length; i++) {
                    if (values[i] != null && !"".equals(values[i])) {
//							value = new String(values[i].getBytes("ISO-8859-1"),"UTF-8") + ",";
                        value += values[i] + ",";
                    }
                }
                if (value != null && !"".equals(value)) {
                    value = value.substring(0, value.length() - 1);
                }
                if (key.equals("keywords")) {//关键字特殊查询字符转义
                    value = value.replace("_", "\\_").replace("%", "\\%");
                }
                returnMap.put(key, value);
            }
        }
        return returnMap;
    }

    /**
     * 读取.ashx文件并且转化为bean对象集合
     *
     * @param t
     * @return
     */
    public static <T extends ModelBO> List<T> ashxStreamToObjectList(String ashxFilePath, String param, T t) {
        InputStream inputStream = null;
        List<T> list = new ArrayList<>();
        try {

            String str = getStream(ashxFilePath);
            list = strToBeanList(t, str);
            URL url = new URL("http://localhost:8082/springboot-handler");
            //根据url获取HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestMethod("GET");//设置请求的参数
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            if (!StringUtils.isBlank(param)) {
                connection.getOutputStream().write(param.getBytes("utf-8"));
            }
            connection.getOutputStream().flush();
            int responseCode = connection.getResponseCode();
            if (responseCode == connection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                int n = 0;
                byte[] datas = new byte[2048];
                while ((n = inputStream.read()) != -1) {
                    bs.write(datas, 0, n);
                }
                bs.flush();
                String result = new String(bs.toByteArray(), "utf-8");
                list = strToBeanList(t, result);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  list;
    }

    /**
     * 字符串转化为javabean对象集合
     * @param t
     * @param str
     * @param <T>
     */
    private static <T extends ModelBO> List<T> strToBeanList(T t, String str) {
        List<T> handlerList = new ArrayList<>();
        //1、使用JSONObject
        JSONObject js = JSONObject.fromObject(str);
        for (int i = 0; i < js.size(); i++) {
            JSONObject json=js.getJSONObject(String.valueOf(i));
            T handlerVO=(T)JSONObject.toBean(json, t.getClass());
            handlerList.add(handlerVO);
        }
        return handlerList;
    }

    private static String getStream(String path) {
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        try {
            File file = new File(path);
            fis = new FileInputStream(file);
            // 建立一个输入流对象reader
            reader = new InputStreamReader(fis, "GB2312");
            //存入缓存
            br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭io流
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}