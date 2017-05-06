package com.blackniuza.stock.common.util;

import java.io.IOException;

import com.blackniuza.stock.common.constants.HttpConstants;
import com.blackniuza.stock.common.exception.NetworkException;
import com.google.common.base.Charsets;
import com.sun.tools.internal.ws.wsdl.document.http.HTTPConstants;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author niuza
 * @date 2017/5/7
 */
public class HttpUtils {

    private Logger logger = Logger.getLogger(HttpUtils.class);

    private static RequestConfig requestConfig = RequestConfig.custom()
        .setSocketTimeout(HttpConstants.TIME_OUT)
        .setConnectTimeout(HttpConstants.TIME_OUT)
        .setConnectionRequestTimeout(HttpConstants.TIME_OUT)
        .build();

    public static String httpGet(String url) throws NetworkException {
        HttpGet get = new HttpGet(url);
        return httpGet(get);
    }

    private static String httpGet(HttpGet httpGet) throws NetworkException {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, Charsets.UTF_8);
        } catch (Exception e) {
            throw new NetworkException("Failed to get: " + httpGet, e);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                throw new NetworkException("Failed to close client", e);
            }
        }
        return responseContent;
    }

}
