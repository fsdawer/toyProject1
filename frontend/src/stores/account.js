import {defineStore} from 'pinia'

export const useAccountStore = defineStore("account", {
    state: () => ({
        checked: false,
        loggedIn: false,
        accessToken: "", // ①  액세스 토큰 프로퍼티 문자열 타입
    }),
    actions: {
        setChecked(val) {
            this.checked = val;
        },
        setLoggedIn(val) {
            this.loggedIn = val;
        },
        setAccessToken(val) { // ② 액세스 토큰의 값 수정 메서드
            this.accessToken = val;
        },
    },
});