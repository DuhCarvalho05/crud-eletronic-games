import { getAll } from "../api/services.js";

const category = (props) => {
	const { id, name } = props;
	return li(
		a(name)
		.att$("href", `#/catalog/${id}`)
		.class$("text-capitalize fw-medium")
		.style$("text-decoration: none; color: #fff; padding: 0px 4px; width: 100%")
	).class$("category-link d-flex").style$("margin-bottom: 10px;");
}

const categories = () => {
	
	getAll("/category", "categories", category)
	
	return aside(
				div(
					span().innerHTML$(`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmarks" viewBox="0 0 16 16">
						  <path d="M2 4a2 2 0 0 1 2-2h6a2 2 0 0 1 2 2v11.5a.5.5 0 0 1-.777.416L7 13.101l-4.223 2.815A.5.5 0 0 1 2 15.5zm2-1a1 1 0 0 0-1 1v10.566l3.723-2.482a.5.5 0 0 1 .554 0L11 14.566V4a1 1 0 0 0-1-1z"/>
						  <path d="M4.268 1H12a1 1 0 0 1 1 1v11.768l.223.148A.5.5 0 0 0 14 13.5V2a2 2 0 0 0-2-2H6a2 2 0 0 0-1.732 1"/>
						</svg>`),
					span("Categorias").class$("fw-bold")
				).class$("d-flex align-items-center gap-2 mb-3 text-light"),
				ul().id$("categories").style$("list-style: none; padding: 0; margin: 0")
			)
			.class$("col-2 rounded p-3")
			.style$("border: 1px solid #27272a; height: fit-content; position: sticky; top: 70px");
}

export default categories;