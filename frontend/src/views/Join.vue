<script setup>
// 회원가입 컴포넌트
import {reactive} from "vue";
import {join} from "@/services/accountService";
import {useRouter} from "vue-router";

// 반응형 상태
const state = reactive({ // ①
  form: {
    name: "",
    loginId: "",
    loginPw: "",
  },
  errorMessage: ""
});

// 라우터 객체
const router = useRouter(); // ②

// 회원가입 데이터 제출
const submit = async () => { // ③

  if (!state.form.name.trim()) {
    window.alert("이름을 입력해주세요")
    return;
  }

  if (!state.form.loginId.trim()) {
    window.alert("아이디 입력해주세요")
    return;
  }

  if (!state.form.loginPw.trim()) {
    window.alert("비밀번호 입력해주세요")
    return;
  }

  state.errorMessage = "";
  const res = await join(state.form);

  if (res.status === 200) {
    window.alert("회원가입을 완료했습니다.");
    await router.push("/");
    return;
  }

  if (res.status === 409) {
    state.errorMessage = res.data || "이미 가입된 이메일입니다.";
    return;
  }

  state.errorMessage = "회원가입에 실패했습니다. 잠시 후 다시 시도해주세요.";
};
</script>

<template>
  <div class="join">
    <div class="container"> <!-- ④ -->
      <form class="py-5 d-flex flex-column gap-3" @submit.prevent="submit"> <!-- ⑤ -->
        <h1 class="h5 mb-3">회원가입</h1>
        <div class="form-floating">
          <input type="text" class="form-control" id="name" placeholder="이름" v-model="state.form.name">  <!-- ⑥ -->
          <label for="name">이름</label>
        </div>
        <div class="form-floating">
          <input type="email" class="form-control" id="loginId" placeholder="이메일" v-model="state.form.loginId"> <!-- ⑥ -->
          <label for="loginId">이메일</label>
        </div>
        <div class="form-floating">
          <input type="password" class="form-control" id="loginPw" placeholder="패스워드" v-model="state.form.loginPw"> <!-- ⑥ -->
          <label for="loginPw">패스워드</label>
        </div>
        <p class="text-danger mb-0" v-if="state.errorMessage">{{ state.errorMessage }}</p>
        <button type="submit" class="w-100 h6 btn py-3 btn-primary mt-2">회원가입</button> <!-- ⑦ -->
      </form>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.join > .container { // ⑧
  max-width: 576px;
}
</style>
