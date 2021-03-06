---
layout: post
title: 서블릿 필터란?
date: 2019-01-21 11:46:32 +0900
description:
categories: Java
tags: Servlet Filter 
---

## 서블릿 필터란?

서블릿 <-> 서블릿필터 <-> 브라우저

* 서블릿 필터는 서블릿과 브라우저 사이에서 동작한다.
* Request, Response 할때마다 서블릿과 브라우저 사이에서 필터 역활을 수행해준다.

* 예시로 서블릿 필터가 있을경우 기본적으로 브라우저와 서블릿이 통신할 때 서블릿에서 처리했던 작업들을 필터에서 모두 처리해줄 수 있다.

* 필터를 사용하는 목적은 서블릿 상단에서 모든걸 처리하는 것 자체가 비생산적이다. 
* 결국 중복코드를 쓰지 않도록 지양하고, 재사용성과 응집도가 높은 코드를 지향하기 위해서     사용하는것이다.

```java

package my.examples.jdbcboard.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// /* : 어떤 url로 서블릿에 접근하든간에 무조건 이 필터를 거치게 됨. 
@WebFilter(filterName="RequestEncodingFilter", urlPatterns = {"/*"})
public class RequestEncodingFilter implements Filter {
    @Override // 초기화
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("RequestEncodingFilter init!");
    } 

    @Override // 필터
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        System.out.println("RequestEncodingFilter doFilter start!!");
        filterChain.doFilter(servletRequest, servletResponse); 
        // 필터체인을 통해서 여러가지 필터를 통하게 할 수 있다.
        // ex) 로그인 체크 -> url 경로 기억 -> 로그인시 그전 url 경로로 이동 등등
        // 다음 필터를 호출
        // 서블릿 응답후
        System.out.println("RequestEncodingFilter doFilter end!!");
    }

    @Override // 종료
    public void destroy() {
        System.out.println("RequestEncodingFilter destroy!");
    }
}

```
