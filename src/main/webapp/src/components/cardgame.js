const cardgame = (props) => {
	
	const {id, imageName, title} = props;
	
	return div(
	          div(
            	a(
					img("image/" + imageName).class$("w-100 rounded")
            	).class$("d-flex w-100").att$("href", `#/details/${id}`)
				).class$("d-flex rounded position-relative")
	            .style$("border: 1px solid #27272a; width: 200px; height: 300px;"),
	          span(title).style$("font-size: 12px; color: #96969e")
	        )
}

export default cardgame;