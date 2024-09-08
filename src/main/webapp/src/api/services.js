const baseURL = "/crud-eletronic-game"

const getAll = async (uri, anchor, component, refreshFn) => {
	return await fetch(baseURL + uri, {
		method: "GET",
		headers: {
			accept: "appliaction/json",
		},
	})
		.then((response) => {
			if (!response.ok) {
				throw new Error("Erro ao efetuar requisição");
			}
			return response.json();
		})
		.then((data) => {
			console.log(data);

			data.map((data) => {
				document.getElementById(anchor).appendChild(component(data, refreshFn));
			});
		})
		.catch((error) => console.log(error));
};

const getUnique = async (uri, anchor, component) => {
	return await fetch(baseURL + uri, {
		method: "GET",
		headers: {
			accept: "appliaction/json",
		},
	})
		.then((response) => {
			if (!response.ok) {
				throw new Error("Erro ao efetuar requisição");
			}
			return response.json();
		})
		.then((data) => {
			console.log(data);
			document.getElementById(anchor).appendChild(component(data));
		})
		.catch((error) => console.log(error));
}

const onLogin = async (form) => {
	return await fetch(baseURL + "/auth/login", {
		method: "POST",
		headers: {
			accept: "appliaction/json",
		},
		body: form,
	})
		.then((response) => {
			if (!response.ok) {
				throw new Error("Erro ao efetuar requisição");
			}

			if (response.status === 400) {
				throw new Error("Bad request");
			}

			window.location.href = "#/";

			return response.json();
		})
		.then(() => {
			console.log("Logado com sucesso");
		})
		.catch(() => console.log("Erro no servidor"));
}

const onLogOut = async () => {
	return await fetch(baseURL + "/auth/login", {
		method: "DELETE",
		headers: {
			accept: "appliaction/json",
		}
	})
		.then((response) => {
			if (response.status === 400) {
				throw new Error("Bad request");
			}

			if(response.ok){				
				window.location.href = "#/login";
			}
			
			return response.json();
		})
		.then(() => {
			console.log("Deslogado com sucesso");
		})
		.catch(() => console.log("Erro no servidor"));
}

const onSignUp = async (form) => {
	return await fetch(baseURL + "/auth/singup", {
		method: "POST",
		headers: {
			accept: "appliaction/json",
		},
		body: form,
	})
		.then((response) => {
			if (response.status === 400) {
				throw new Error("Bad request");
			}
			
			if (response.status === 409) {
				throw new Error("Email já cadastrado");
			}

			if(response.ok){				
				window.location.href = "#/login";
			}
			
			return response.json();
		})
		.then(() => {
			console.log("Logado com sucesso");
		})
		.catch(() => console.log("Erro no servidor"));
}

const onRegisterCategory = async (form) => {
	return await fetch(baseURL + "/category", {
		method: "POST",
		headers: {
			accept: "appliaction/json",
		},
		body: form,
	})
		.then((response) => {
			if (response.status === 400) {
				throw new Error("Bad request");
			}

			if(response.ok){				
				window.location.href = "#/categories";
			}
			
			return response.json();
		})
		.then(() => {
			console.log("Registrado com sucesso");
		})
		.catch(() => console.log("Erro no servidor"));
}

const onUpdateCategory = async (id, form) =>{
	return await fetch(baseURL + "/category?categoryId=" + id, {
		method: "PUT",
		headers: {
			accept: "appliaction/json",
		},
		body: form,
	})
		.then((response) => {
			if (response.status === 400) {
				throw new Error("Bad request");
			}

			if(response.ok){				
				window.location.href = "#/categories";
			}
			
			return response.json();
		})
		.then(() => {
			console.log("Editado com sucesso");
		})
		.catch(() => console.log("Erro no servidor"));
}

const onDeleteCategory =  async (id) => {
	return await fetch(baseURL + "/category?categoryId=" + id, {
		method: "DELETE",
		headers: {
			accept: "appliaction/json",
		},
		body: form,
	})
		.then((response) => {
			if (response.status === 400) {
				throw new Error("Bad request");
			}

			if(response.ok){				
				window.location.href = "#/categories";
			}
			
			return response.json();
		})
		.then(() => {
			console.log("Editado com sucesso");
		})
		.catch(() => console.log("Erro no servidor"));
}



export { 
	getAll, 
	getUnique, 
	onLogin, 
	onSignUp, 
	onRegisterCategory, 
	onUpdateCategory, 
	onDeleteCategory,
	onLogOut,
	};