import { onLogin } from "../../api/services.js";
import layout from "../../layouts/layout.js";

const login = () => {
	
	const handleLogin = async (e) => {
		e.preventDefault();
		
		const form = new FormData(document.getElementById("login"));
		
		const email = form.get("email");
		const password = form.get("password");
		console.log(JSON.stringify({email, password}));
		await onLogin(JSON.stringify({email, password}));
	}
	
	const handlePressEnter = (e) => {
		if(e.keyCode === 13){
			e.preventDefault();
			handleLogin(e);
		}
	}
	
	return layout(
		div(
			div(
				span("Acessar sua conta")
				.class$("fw-bold fs-4 text-center"),
				form(
					div(
						input()
						.class$("form-control me-2 input")
						.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
						.att$("type", "email")
						.att$("name", "email")
						.att$("placeholder", "name@example.com")
					)
					.class$("mb-3"),
					div(
						input()
						.class$("form-control me-2 input")
						.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
						.att$("type", "password")
						.att$("name", "password")
						.att$("placeholder", "sua senha")
					)
					.class$("mb-3"),
					div(
						button("Entrar")
						.class$("btn btn-light btn-sm w-100")
						.att$("type", "submit")
						.onclick$(handleLogin)
					)
					.class$("mb-3"),
					div(
						span("Não possui uma conta? "),
						a("Cadastre-se")
						.class$("text-light")
						.att$("href", "#/singup")
					)
					.class$("mb-3")
				)
				.id$("login")
				.att$("method", "POST")
				.onkeydown$(handlePressEnter)
			)
			.class$("p-3 text-center position-absolute top-50 start-50 translate-middle text-light")
			.style$("width: 400px")
		)
		.class$("container position-relative")
		.style$("height: calc(100dvh - 160px)")
	);
}

export default login;