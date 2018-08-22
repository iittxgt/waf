package info.yangguo.waf.request.forward.http;

import info.yangguo.waf.Constant;
import info.yangguo.waf.request.forward.ForwardProcess;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.InputStream;
import java.util.Map;

public class Swagger2 implements ForwardProcess {
    private static HttpClient httpClient;

    static {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(10);
        connectionManager.setDefaultMaxPerRoute(10);

        RequestConfig requestConfig = RequestConfig.custom()
                .build();

        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }

    @Override
    public String getWafRoutePattern() {
        return ".*";
    }

    @Override
    public HttpResponse execute(String wafRoute, String uri, Map<String, Object> args) {
        HttpResponse result = null;
        try {
            if (uri.endsWith("swagger-resources/configuration/ui")) {
                String content = "{\"apisSorter\":\"alpha\",\"jsonEditor\":false,\"showRequestHeaders\":false,\"deepLinking\":true,\"displayOperationId\":false,\"defaultModelsExpandDepth\":1,\"defaultModelExpandDepth\":1,\"defaultModelRendering\":\"example\",\"displayRequestDuration\":false,\"docExpansion\":\"none\",\"filter\":false,\"operationsSorter\":\"alpha\",\"showExtensions\":false,\"tagsSorter\":\"alpha\"}";
                result = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes()));
            } else if (uri.endsWith("swagger-resources/configuration/security")) {
                String content = "{}";
                result = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes()));
            } else if (uri.endsWith("swagger-resources")) {
                String content = "[{\"name\":\"default\",\"url\":\"/v2/api-docs\",\"swaggerVersion\":\"2.0\",\"location\":\"/v2/api-docs\"}]";
                result = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes()));
            } else if (uri.endsWith("v2/api-docs")) {
                String content = "{\"swagger\":\"2.0\",\"info\":{\"description\":\"DESCRIPTION\",\"version\":\"VERSION\",\"title\":\"TITLE\",\"termsOfService\":\"https://github.com/chengdedeng/waf\",\"license\":{\"name\":\"Apache License 2.0\",\"url\":\"https://en.wikipedia.org/wiki/Apache_License\"}},\"basePath\":\"/\",\"tags\":[{\"name\":\"config-controller\",\"description\":\"配置相关的接口\"},{\"name\":\"user-controller\",\"description\":\"用户相关的接口\"}],\"paths\":{\"/api/config/forward/http/upstream\":{\"get\":{\"tags\":[\"config-controller\"],\"summary\":\"获取Upstream配置\",\"operationId\":\"getUpstreamConfigsUsingGET\",\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果«List«UpstreamConfig»»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"put\":{\"tags\":[\"config-controller\"],\"summary\":\"设置Upstream\",\"operationId\":\"setUpstreamConfigUsingPUT_1\",\"consumes\":[\"application/json\"],\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/UpstreamConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"delete\":{\"tags\":[\"config-controller\"],\"summary\":\"删除Upstream\",\"operationId\":\"deleteUpstreamUsingDELETE\",\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/UpstreamConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"204\":{\"description\":\"No Content\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"}}}},\"/api/config/forward/http/upstream/server\":{\"put\":{\"tags\":[\"config-controller\"],\"summary\":\"设置Upstream Server\",\"operationId\":\"setUpstreamConfigUsingPUT\",\"consumes\":[\"application/json\"],\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/UpstreamServerConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"delete\":{\"tags\":[\"config-controller\"],\"summary\":\"删除Upstream Server\",\"operationId\":\"deleteUpstreamServerUsingDELETE\",\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/UpstreamServerConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"204\":{\"description\":\"No Content\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"}}}},\"/api/config/redirect\":{\"get\":{\"tags\":[\"config-controller\"],\"summary\":\"获取Redirect配置\",\"operationId\":\"getRedirectConfigsUsingGET\",\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果«List«RedirectConfigDto»»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"put\":{\"tags\":[\"config-controller\"],\"summary\":\"设置redirect\",\"operationId\":\"setRedirectConfigUsingPUT\",\"consumes\":[\"application/json\"],\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/RedirectConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"delete\":{\"tags\":[\"config-controller\"],\"summary\":\"删除redirect\",\"operationId\":\"deleteRedirectUsingDELETE\",\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/RedirectConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"204\":{\"description\":\"No Content\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"}}}},\"/api/config/response\":{\"get\":{\"tags\":[\"config-controller\"],\"summary\":\"获取ResponseFilter配置\",\"operationId\":\"getResponseConfigsUsingGET\",\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果«List«ResponseConfig»»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"put\":{\"tags\":[\"config-controller\"],\"summary\":\"设置ResponseFilter\",\"operationId\":\"setResponseConfigUsingPUT\",\"consumes\":[\"application/json\"],\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/ResponseConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}},\"/api/config/rewrite\":{\"get\":{\"tags\":[\"config-controller\"],\"summary\":\"获取Rewrite配置\",\"operationId\":\"getRewriteConfigsUsingGET\",\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果«List«RewriteConfigDto»»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"put\":{\"tags\":[\"config-controller\"],\"summary\":\"设置rewrite\",\"operationId\":\"setRewriteConfigUsingPUT\",\"consumes\":[\"application/json\"],\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/RewriteConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"delete\":{\"tags\":[\"config-controller\"],\"summary\":\"删除rewrite\",\"operationId\":\"deleteRewriteUsingDELETE\",\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/RewriteConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"204\":{\"description\":\"No Content\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"}}}},\"/api/config/security\":{\"get\":{\"tags\":[\"config-controller\"],\"summary\":\"获取SecurityFilter配置\",\"operationId\":\"getSecurityConfigsUsingGET\",\"produces\":[\"*/*\"],\"parameters\":[{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果«List«SecurityConfig»»\"}},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"put\":{\"tags\":[\"config-controller\"],\"summary\":\"设置SecurityFilter\",\"operationId\":\"setRequestConfigUsingPUT_1\",\"consumes\":[\"application/json\"],\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/SecurityConfigDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}},\"/api/config/security/iterm\":{\"put\":{\"tags\":[\"config-controller\"],\"summary\":\"设置SecurityFilter Iterm\",\"operationId\":\"setRequestConfigUsingPUT\",\"consumes\":[\"application/json\"],\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/SecurityConfigItermDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}},\"delete\":{\"tags\":[\"config-controller\"],\"summary\":\"删除SecurityFilter Iterm\",\"operationId\":\"deleteRequestItermUsingDELETE\",\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"dto\",\"description\":\"dto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/SecurityConfigItermDto\"}},{\"name\":\"WAFTOKEN\",\"in\":\"cookie\",\"description\":\"WAFTOKEN\",\"required\":false,\"type\":\"string\"}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"204\":{\"description\":\"No Content\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"}}}},\"/api/user/login\":{\"post\":{\"tags\":[\"user-controller\"],\"summary\":\"登录\",\"operationId\":\"loginUsingPOST\",\"consumes\":[\"application/json\"],\"produces\":[\"*/*\"],\"parameters\":[{\"in\":\"body\",\"name\":\"userDto\",\"description\":\"userDto\",\"required\":true,\"schema\":{\"$ref\":\"#/definitions/UserDto\"}}],\"responses\":{\"200\":{\"description\":\"OK\",\"schema\":{\"$ref\":\"#/definitions/响应结果\"}},\"201\":{\"description\":\"Created\"},\"401\":{\"description\":\"Unauthorized\"},\"403\":{\"description\":\"Forbidden\"},\"404\":{\"description\":\"Not Found\"}}}}},\"definitions\":{\"BasicConfig\":{\"type\":\"object\",\"properties\":{\"extension\":{\"type\":\"object\"},\"isStart\":{\"type\":\"boolean\"}},\"title\":\"BasicConfig\"},\"RedirectConfigDto\":{\"type\":\"object\",\"required\":[\"wafRoute\"],\"properties\":{\"isStart\":{\"type\":\"boolean\",\"example\":false,\"description\":\"开关，true启用，false关闭。\",\"allowEmptyValue\":false},\"wafRoute\":{\"type\":\"string\",\"description\":\"x-waf-route，路由标志。\",\"allowEmptyValue\":false},\"iterms\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}},\"title\":\"RedirectConfigDto\"},\"ResponseConfig\":{\"type\":\"object\",\"properties\":{\"config\":{\"$ref\":\"#/definitions/BasicConfig\"},\"filterName\":{\"type\":\"string\"}},\"title\":\"ResponseConfig\"},\"ResponseConfigDto\":{\"type\":\"object\",\"required\":[\"filterName\"],\"properties\":{\"filterName\":{\"type\":\"string\",\"description\":\"Response拦截器名称。\",\"allowEmptyValue\":false},\"isStart\":{\"type\":\"boolean\",\"example\":false,\"description\":\"是否开启，true启用，false关闭。\",\"allowEmptyValue\":false}},\"title\":\"ResponseConfigDto\"},\"RewriteConfigDto\":{\"type\":\"object\",\"required\":[\"wafRoute\"],\"properties\":{\"isStart\":{\"type\":\"boolean\",\"example\":false,\"description\":\"开关，true启用，false关闭。\",\"allowEmptyValue\":false},\"wafRoute\":{\"type\":\"string\",\"description\":\"x-waf-route，路由标志。\",\"allowEmptyValue\":false},\"iterms\":{\"type\":\"array\",\"items\":{\"type\":\"string\"}}},\"title\":\"RewriteConfigDto\"},\"SecurityConfig\":{\"type\":\"object\",\"properties\":{\"config\":{\"$ref\":\"#/definitions/BasicConfig\"},\"filterName\":{\"type\":\"string\"},\"securityConfigIterms\":{\"type\":\"array\",\"items\":{\"$ref\":\"#/definitions/SecurityConfigIterm\"}}},\"title\":\"SecurityConfig\"},\"SecurityConfigDto\":{\"type\":\"object\",\"required\":[\"filterName\"],\"properties\":{\"filterName\":{\"type\":\"string\",\"description\":\"Security拦截器名称。\",\"allowEmptyValue\":false},\"isStart\":{\"type\":\"boolean\",\"example\":false,\"description\":\"是否开启，true启用，false关闭。\",\"allowEmptyValue\":false}},\"title\":\"SecurityConfigDto\"},\"SecurityConfigIterm\":{\"type\":\"object\",\"properties\":{\"config\":{\"$ref\":\"#/definitions/BasicConfig\"},\"name\":{\"type\":\"string\"}},\"title\":\"SecurityConfigIterm\"},\"SecurityConfigItermDto\":{\"type\":\"object\",\"required\":[\"filterName\",\"name\"],\"properties\":{\"extension\":{\"type\":\"object\",\"description\":\"Iterm扩展信息，目前只在CCSecurityFilter有使用。\",\"allowEmptyValue\":false},\"filterName\":{\"type\":\"string\",\"description\":\"Security拦截器名称。\",\"allowEmptyValue\":false},\"isStart\":{\"type\":\"boolean\",\"example\":false,\"description\":\"是否开启，true启用，false关闭。\",\"allowEmptyValue\":false},\"name\":{\"type\":\"string\",\"description\":\"配置项名称。\",\"allowEmptyValue\":false}},\"title\":\"SecurityConfigItermDto\"},\"ServerBasicConfig\":{\"type\":\"object\",\"properties\":{\"extension\":{\"type\":\"object\"},\"isStart\":{\"type\":\"boolean\"},\"weight\":{\"type\":\"integer\",\"format\":\"int32\"}},\"title\":\"ServerBasicConfig\"},\"ServerConfig\":{\"type\":\"object\",\"properties\":{\"config\":{\"$ref\":\"#/definitions/ServerBasicConfig\"},\"ip\":{\"type\":\"string\"},\"isHealth\":{\"type\":\"boolean\"},\"port\":{\"type\":\"integer\",\"format\":\"int32\"}},\"title\":\"ServerConfig\"},\"UpstreamConfig\":{\"type\":\"object\",\"properties\":{\"config\":{\"$ref\":\"#/definitions/BasicConfig\"},\"serverConfigs\":{\"type\":\"array\",\"items\":{\"$ref\":\"#/definitions/ServerConfig\"}},\"wafRoute\":{\"type\":\"string\"}},\"title\":\"UpstreamConfig\"},\"UpstreamConfigDto\":{\"type\":\"object\",\"required\":[\"wafRoute\"],\"properties\":{\"isStart\":{\"type\":\"boolean\",\"example\":false,\"description\":\"开关，true启用，false关闭。\",\"allowEmptyValue\":false},\"wafRoute\":{\"type\":\"string\",\"description\":\"x-waf-route，路由标志。\",\"allowEmptyValue\":false}},\"title\":\"UpstreamConfigDto\"},\"UpstreamServerConfigDto\":{\"type\":\"object\",\"required\":[\"ip\",\"port\",\"wafRoute\"],\"properties\":{\"ip\":{\"type\":\"string\",\"description\":\"IP地址。\",\"allowEmptyValue\":false},\"isStart\":{\"type\":\"boolean\",\"example\":false,\"description\":\"开关，true启用，false关闭。\",\"allowEmptyValue\":false},\"port\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"端口。\",\"allowEmptyValue\":false},\"wafRoute\":{\"type\":\"string\",\"description\":\"x-waf-route，路由标志。\",\"allowEmptyValue\":false},\"weight\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"权重。\",\"allowEmptyValue\":false}},\"title\":\"UpstreamServerConfigDto\"},\"UserDto\":{\"type\":\"object\",\"required\":[\"email\",\"password\"],\"properties\":{\"email\":{\"type\":\"string\",\"description\":\"邮箱。\",\"allowEmptyValue\":false},\"password\":{\"type\":\"string\",\"description\":\"密码。\",\"allowEmptyValue\":false}},\"title\":\"UserDto\"},\"响应结果\":{\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"结果代码。\",\"allowEmptyValue\":false},\"value\":{\"type\":\"object\",\"description\":\"结果对象。\",\"allowEmptyValue\":false}},\"title\":\"响应结果\"},\"响应结果«List«RedirectConfigDto»»\":{\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"结果代码。\",\"allowEmptyValue\":false},\"value\":{\"type\":\"array\",\"description\":\"结果对象。\",\"allowEmptyValue\":false,\"items\":{\"$ref\":\"#/definitions/RedirectConfigDto\"}}},\"title\":\"响应结果«List«RedirectConfigDto»»\"},\"响应结果«List«ResponseConfig»»\":{\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"结果代码。\",\"allowEmptyValue\":false},\"value\":{\"type\":\"array\",\"description\":\"结果对象。\",\"allowEmptyValue\":false,\"items\":{\"$ref\":\"#/definitions/ResponseConfig\"}}},\"title\":\"响应结果«List«ResponseConfig»»\"},\"响应结果«List«RewriteConfigDto»»\":{\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"结果代码。\",\"allowEmptyValue\":false},\"value\":{\"type\":\"array\",\"description\":\"结果对象。\",\"allowEmptyValue\":false,\"items\":{\"$ref\":\"#/definitions/RewriteConfigDto\"}}},\"title\":\"响应结果«List«RewriteConfigDto»»\"},\"响应结果«List«SecurityConfig»»\":{\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"结果代码。\",\"allowEmptyValue\":false},\"value\":{\"type\":\"array\",\"description\":\"结果对象。\",\"allowEmptyValue\":false,\"items\":{\"$ref\":\"#/definitions/SecurityConfig\"}}},\"title\":\"响应结果«List«SecurityConfig»»\"},\"响应结果«List«UpstreamConfig»»\":{\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"integer\",\"format\":\"int32\",\"description\":\"结果代码。\",\"allowEmptyValue\":false},\"value\":{\"type\":\"array\",\"description\":\"结果对象。\",\"allowEmptyValue\":false,\"items\":{\"$ref\":\"#/definitions/UpstreamConfig\"}}},\"title\":\"响应结果«List«UpstreamConfig»»\"}}}";
                result = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content.getBytes()));
            } else {
                HttpUriRequest httpUriRequest = new HttpGet("http://127.0.0.1:" + Constant.wafWebConfs.get("server.port") + uri);
                org.apache.http.HttpResponse response = httpClient.execute(httpUriRequest);
                if (200 == response.getStatusLine().getStatusCode()) {
                    try (InputStream inputStream = response.getEntity().getContent()) {
                        byte[] content = IOUtils.toByteArray(inputStream);
                        result = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(content));
                    }

                }
            }
        } catch (Exception e) {
            result = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_GATEWAY);
        }
        if (result != null) {
            HttpHeaders httpHeaders = new DefaultHttpHeaders();
            httpHeaders.add("Transfer-Encoding", "chunked");
            result.headers().add(httpHeaders);
        }
        return result;
    }
}
