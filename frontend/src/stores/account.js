import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

// 계정 데이터를 다루는 계정 스토어
export const useAccountStore = defineStore("account", { // ①
    state: () => ({
        checked: false, //  사용자의 로그인 체크 여부를
        loggedIn: false, //  사용자의 로그인
    }),
    actions: {
        setChecked(val) { //
            this.checked = val;
        },
        setLoggedIn(val) { //
            this.loggedIn = val;
        },
    },
});


