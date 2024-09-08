import { getUnique } from "../../api/services.js";
import cardgame from "../../components/cardgame.js";
import layout from "../../layouts/layout.js";

const details = (props) => {
  const gameId = props[0];
	
  getUnique(`/game/${gameId}`, "content", cardgame);

  return layout(div().id$("content"));
};

export default details;
