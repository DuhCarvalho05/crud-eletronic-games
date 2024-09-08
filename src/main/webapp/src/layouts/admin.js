import { getUserSession } from "../auth/session.js"
import layout from "./layout.js";

const adminLayout = (...children) => {
	
	const userSession = getUserSession();
	
	if(userSession ===  null || userSession.type !== "ADMIN") window.location.href = "#/";
	
	return layout(...children)
}

export default adminLayout;