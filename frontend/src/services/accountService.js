//계정 서비스 구현
// 백엔드와 연결하는 곳은 services에 JS
import httpRequester from "@/libs/httpRequester";

// 회원가입
export const join = (args) => {
    return httpRequester.post("/v2/api/account/join", args).catch(e => e.response);
}

// 로그인
// 로그인을 처리하는 메서드 Http post 메서드로 로그인 api 호출 / 응답 값 리턴하는 기능
export const login = (args) => {
    return httpRequester.post("/v2/api/account/login", args).catch(e => e.response);
}


// 로그인 여부 확인
export const check = () => {
    return httpRequester.get("/v2/api/account/check").catch(e => e.response);
};

// 로그아웃
// http Post메서드로 로그아웃 api 호출
export const logout = () => {
    return httpRequester.post("/v2/api/account/logout").catch(e => e.response)
}
