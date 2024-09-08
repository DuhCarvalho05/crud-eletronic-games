import { onSignUp } from "../../api/services.js";
import layout from "../../layouts/layout.js";

const singup = () => {
	
	const handleSignUp = async (e) => {
		e.preventDefault();
		const form = new FormData(document.getElementById("signup"));
		
		const name = form.get("name");
		const email = form.get("email");
		const password = form.get("password");
		
		await onSignUp(JSON.stringify({name, email, password}));
	}
	
	const handlePressEnter = (e) => {
		if(e.keyCode === 13){
			e.preventDefault();
			onSignUp(e);
		}
	}
	
	return layout(
		div(
			div(
				span("Criar sua conta")
				.class$("fw-bold fs-4 text-center"),
				form(
					div(
						input()
						.class$("form-control me-2 input")
						.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
						.att$("type", "text")
						.att$("name", "name")
						.att$("placeholder", "ex: João Silva")
					)
					.class$("mb-3"),
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
						.onclick$(handleSignUp)
					)
					.class$("mb-3"),
					div(
						span("Já possui uma conta? "),
						a("Acessar conta")
						.class$("text-light")
						.att$("href", "#/login")
					)
					.class$("mb-3")
				)
				.id$("signup")
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

export default singup;