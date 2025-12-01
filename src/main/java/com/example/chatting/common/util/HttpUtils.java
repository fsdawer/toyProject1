package com.example.chatting.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HttpUtils {

    // 세션 입력
    public static void setSession(HttpServletRequest request,  String key, Object value) {
        request.getSession().setAttribute(key, value);

    }


    // 세션 값 조회
    public static Object getSession(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);

    }


    // 세션 삭제
    public static void removeSession(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);

    }



    // 쿠키 입력 : 쿠키를 입력하는 메서드, 매개변수로 HTTP 응답 객체와 쿠키의 이름, 값, 유효시간(초)를 받고,
    //           매개변수로 받은 유효시간이 0 이하라면 유효시간을 지정하지 않는데 이렇게 하면 웹 브라우저가 종료될 때 쿠키도 삭제됨
    public static void setCookie(HttpServletResponse response, String name, String value, int expSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);     // HttpOnly 옵션을 true 로 설정하여 해당 쿠키를 서버에서만 접근 가능하도록 설정
        cookie.setPath("/"); // 사이트 전체에서 쿠키가 항상 보내짐

        if(expSeconds > 0){ // 유효시간이 0 이상이라면
            cookie.setMaxAge(expSeconds);
        }
        response.addCookie(cookie); // 서버가 브라우저에게 쿠키를 보내는 단계
    }


    // ④ 쿠키 값 조회 : 특정 쿠키의 값을 조회하는 메서드. 매개변수로 HTTP 요청 객체와 쿠키의 이름을 받습니다.
    // [ Cookie("JSESSIONID", "12ADFS32DAF"), Cookie("loginToken", "XYZ123456")]
    //cookie.getName() → "JSESSIONID" -> key(name)
    //cookie.getValue() → "12ADFS32DAF" -> value
    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if(cookies != null){   // 쿠키가 null이 아니라면
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){ // cookie.getName() → 브라우저가 보낸 “실제 쿠키 이름”  / equals(name) → 메서드 호출할 때 전달한 “찾고 싶은 쿠키 이름”
                    return cookie.getValue(); // 그 쿠키의 value를 반환한다
                }
            }
        }
        return null;
    }

    // ⑤ 쿠키 삭제 : 특정 쿠키 삭제 메서드. 매개변수로 HTTP 요청객체와 쿠키 이름을 받아 삭제합니다.
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    // 토큰 조회
    public static String getBearerToken(HttpServletRequest req) {
        String authorization = req.getHeader("Authorization");

        if (authorization != null) {
            return authorization.replace("Bearer ", "").trim(); // Bearer 값 조회
        }

        return null;
    }
}