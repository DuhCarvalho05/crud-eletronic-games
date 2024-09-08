const getUserSession = () => {
	const user = sessionStorage.getItem("user");
	if(user !== null) return JSON.parse(user);
	return null;
}

const setUserSession = (user) => {
	sessionStorage.setItem("user", JSON.stringify(user));
}

const removeUserSession = () => {
	sessionStorage.removeItem("user");
}

export { getUserSession, setUserSession, removeUserSession }