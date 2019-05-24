---
layout: post
title: "Spring-Security 환경설정 및 로그인화면 구현 2"
date: 2018-04-13 12:46:32 +0900
description:
categories: [Spring]
tags: [Java, Spring, spring-security]
---

# 로그인 페이지 커스터마이징

- `<form-login>` 요소를 이용하면 손쉽게 로그인 페이지를 커스터마이징할 수 있다.
- security-context.xml 에 아래와 같이 지정.

{% highlight xml %}  
<http auto-config='true' >
<form-login login-page="/loginForm.html" authentication-failure-url="/loginForm.html?ng" />
<intercept-url pattern="/login.html*" access="ROLE_USER"/>
<intercept-url pattern="/welcome.html*" access="ROLE_ADMIN"/>
</http>
{% endhighlight%}

# 클라이언트요청을 처리하기 위한 컨트롤러

{% highlight Java %}  
@RequestMapping("/login.html")
public String login(Locale locale, Model model){
  
 return "security/login";
}

@RequestMapping("/welcome.html")
public String welcome(Locale locale, Model model){
  
 return "security/welcome";
}

@RequestMapping("/loginForm.html")
public String loginForm(Locale locale, Model model){
  
 return "security/loginForm";
}
{% endhighlight %}

# view구성

- loginForm.jsp
  {% highlight Javascript %}

  <h1>loginForm.jsp</h1>
  <c:url value="j_spring_security_check" var="loginUrl"/>
  <form action="${loginUrl}" method="post">
  	<c:if test="${param.ng != null}">
  	<p>
  		LogIn NG! <br />
  		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != NULL}">
  			message : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
  		</c:if>
  	</p>
  	</c:if>
  	ID : <input type="text" name="j_username"> <br />
  	PW : <input type="text" name="j_password"> <br />
  	<input type="submit" value="LOGIN"> <br />
  </form>
  {% endhighlight %}

- login.jsp : 태그라이브러리 추가 `<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>`
  {% highlight Javascript %}
  <h1>login.jsp</h1>
  <s:authorize ifAnyGranted="ROLE_USER">
  <p> is Log-In</p>
  </s:authorize>

<s:authorize ifNotGranted="ROLE_USER">

<p> is Log-Out</p>
</s:authorize>

<%-- USER ID : ${pageContext.request.userPrincipal.name}<br/> --%>
USER ID : <s:authentication property="name"/><br/>
<a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a> <br />
{% endhighlight %}

#### 출처

- [2장 Intecept와 Granted Authority](http://egloos.zum.com/springmvc/v/506465)
- 인프런
