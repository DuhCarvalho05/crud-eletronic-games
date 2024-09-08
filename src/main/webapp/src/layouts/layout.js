import header from "../components/header.js";

const layout = (...children) => {
	
	return div(
			header(), 
			main(...children).class$("container text-light").style$("margin-top: 90px")
		);
};

export default layout;
