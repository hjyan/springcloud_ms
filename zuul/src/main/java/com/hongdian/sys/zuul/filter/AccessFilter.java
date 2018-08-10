package com.hongdian.sys.zuul.filter;

import java.util.HashMap;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hongdian.sys.common.entity.JWTInfo;
import com.hongdian.sys.common.util.JSONUtils;
import com.hongdian.sys.common.util.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
@WebFilter
public class AccessFilter extends ZuulFilter {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${zuul.ignored-url.login}")
    private String loginPath;

    @Value("${zuul.ignored-url.logout}")
    private String logoutPath;

    @Value("${zuul.prefix}")
    private String zuulPrefix;

    @Value("${token.header}")
    private String tokenHeader;

    @Value(value = "${jwt.pubKeyPath}")
    private String pubKeyPath;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
        //登陆请求token
        if (isStartWith(requestUri)) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                ctx.addZuulRequestHeader(cookie.getName(), cookie.getValue());
            }
        }
//		Map<String, String[]> parameterMap = request.getParameterMap();
//		if(parameterMap != null) {
//			Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
//			for(Entry<String, String[]> entry : entrySet) {
//				ctx.addZuulRequestHeader(entry.getKey(), entry.getValue()[0]);
//			}
//		}
        JWTInfo user = null;
        try {
            user = getJWTUser(request, ctx);
            if (user == null) {
                throw new RuntimeException(" Invalid Token ! ......");
            }
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, String> info = new HashMap<>();
            info.put("message", "Invalid Token ! ......");
            setFailedRequest(JSONUtils.writeValueAsString(info), 200);
            return null;
        }
//        ctx.addZuulRequestHeader(tokenHeader, request.getParameter(tokenHeader));
        return null;
    }

    private JWTInfo getJWTUser(HttpServletRequest request, RequestContext ctx) throws Exception {
        String authToken = request.getHeader(tokenHeader);
        if (StringUtils.isBlank(authToken)) {
            authToken = request.getParameter(tokenHeader);
        }
        ctx.addZuulRequestHeader(tokenHeader, authToken);

        return JwtUtils.getInfoFromToken(authToken, pubKeyPath);
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        if (requestUri.startsWith(loginPath) || requestUri.startsWith(logoutPath)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

}
