import axios from "axios"; // ①
// // 백엔드와 연결하는 곳은 services에 JS

// 상품 목록 조회
export const getItems = () => { // ②
    return axios.get("/v2/api/items").catch(e => e.response);
};