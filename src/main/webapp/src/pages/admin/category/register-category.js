import layout from "../../../layouts/layout.js"
import { onRegisterCategory } from "../../../api/services.js"

const registerCategory = () => {

	const handleRegisterCategory = async (e) => {
		e.preventDefault();

		const form = new FormData(document.getElementById("register-category"));

		const name = form.get("name");

		await onRegisterCategory(JSON.stringify({ name }));
	}

	const handlePressEnter = (e) => {
		if (e.keyCode === 13) {
			e.preventDefault();
			handleRegisterCategory(e);
		}
	}

	return layout(
		div(
			div(
				span("Nova categoria").class$("fw-bold fs-4 text-center"),
				form(
					div(
						label("Nome categoria").class$("form-label"),
						input("text")
							.class$("form-control me-2 input")
							.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
							.att$("name", "name")
							.att$("placeholder", "Nome categoria")
					).class$("mb-3"),
					div(
						a("Cancelar")
							.class$("btn btn-secondary btn-sm")
							.att$("href", "#/categories"),
						button("Cadastrar categoria")
							.class$("btn btn-light btn-sm")
							.att$("type", "submit")
							.onclick$(handleRegisterCategory)
					).class$("d-flex gap-3 justify-content-end")
				)
					.id$("register-category")
					.att$("method", "POST")
					.onkeydown$(handlePressEnter)
			).class$("w-50 p-3")
		).class$("w-full d-flex justify-content-center mt-5")
	)
}

export default registerCategory;