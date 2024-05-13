import { useNavigate, useLocation } from "react-router-dom";
import { useEffect} from "react";
import {loginAPI} from "../api/loginAPI.tsx";


export default function KakaoRedirectPage(){
    const location = useLocation();
    const navigate = useNavigate();

    const handleOAuthKakao = async (code: string) => {
        try {
            // 카카오로부터 받아온 code를 서버에 전달하여 카카오로 회원가입 & 로그인한다
            const response = await loginAPI.getKakaoLogin(code);
            const data = response.data; // 응답 데이터
            console.log("로그인 성공: " + JSON.stringify(data));
            console.log("accessToken: " + data.accessToken);

            //토큰 받아서 localstorage에 저장
            localStorage.setItem("accessToken", data.accessToken);
            navigate("/write-song");  // 성공 시 리다이렉트할 경로
        } catch (error) {
            console.error("로그인 실패", error);
            navigate("/");  // 실패 시 리다이렉트할 경로
        }
    };

    useEffect(() => {
        const searchParams = new URLSearchParams(location.search);
        const code = searchParams.get('code');  // 카카오는 Redirect 시키면서 code를 쿼리 스트링으로 준다.
        if (code) {
            // alert("CODE = " + code);
            handleOAuthKakao(code);
        }
    }, []);

    return (
        <div>
            <div>Processing...</div>
        </div>

    );

}