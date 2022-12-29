package com.wechat.pay.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author daibo
 * @date 2022/12/29 17:36
 */
public abstract class WechatPayService {

    protected static final Logger _Logger = LoggerFactory.getLogger(WechatPayV3service.class);


    /**
     * 最大链接数
     */
    public static int DEFAULT_MAX_TOTAL = 20;
    /**
     * 最大路由链接数，根据连接到的主机对MaxTotal的一个细分
     */
    public static int DEFAULT_MAX_PER_ROUTE = 20;

    /**
     * HTTP连接管理
     */
    protected PoolingHttpClientConnectionManager httpClientManager;
    /**
     * 请求参数
     */
    protected RequestConfig httpParams;
    /**
     * 网络参数
     */
    protected SocketConfig socketParams;

    protected HttpClientBuilder builder;

    public WechatPayService(int connectionSecond, int soSecond) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 将最大连接数增加
        cm.setMaxTotal(DEFAULT_MAX_TOTAL);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        httpClientManager = cm;
        httpParams = RequestConfig.custom().setConnectionRequestTimeout(connectionSecond * 1000)
                .setSocketTimeout(soSecond * 1000).build();
        socketParams = SocketConfig.custom().setSoTimeout(soSecond * 1000).build();
    }


    protected CloseableHttpClient getClient() {
        httpClientManager.closeExpiredConnections();
        httpClientManager.closeIdleConnections(1, TimeUnit.HOURS);
        HttpClientBuilder builder = newBuilder().setConnectionManager(httpClientManager)
                .setDefaultRequestConfig(httpParams).setDefaultSocketConfig(socketParams);
        return builder.build();
    }

    protected HttpClientBuilder newBuilder() {
        if (null == builder) {
            builder = createBuilder();
        }
        return builder;
    }

    protected abstract HttpClientBuilder createBuilder();

    protected abstract String toString(Object request);

    protected abstract <E> E fromString(String result, Class<E> clazz);


    public <RESPONSE, REQUEST> RESPONSE exe(String path, REQUEST request, Class<RESPONSE> clazz) throws IOException, WechatApiException {
        String result = doExe(path, toString(request));
        if (null == clazz) {
            return null;
        }
        return fromString(result, clazz);
    }

    protected String doExe(String path, String content) throws IOException, WechatApiException {
        String host = WechatUrl.getUrl();
        String url = host + path;
        IOException last = null;
        for (int i = 0; i < 3; i++) {
            boolean success = false;
            try {
                String result = doExe(URI.create(url), content);
                success = true;
                return result;
            } catch (HttpHostConnectException | NoHttpResponseException e) {
                _Logger.warn("{}请求异常", url);
                last = e;
            } catch (SocketTimeoutException | ConnectTimeoutException e) {
                _Logger.warn("{}链接异常", url);
                last = e;
            } catch (IOException e) {
                _Logger.warn("{}IO异常", url);
                last = e;
            } finally {
                if (!success) {
                    WechatUrl.switchUrl();
                }
            }
        }
        throw last;
    }

    protected abstract String doExe(URI uri, String content) throws IOException, WechatApiException;
}
