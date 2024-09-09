import home from "./src/pages/home/home.js";
import catalog from "./src/pages/catalog/catalog.js";
import details from "./src/pages/details/details.js";
import search from "./src/pages/search/search.js";
import login from "./src/pages/login/login.js";
import singup from "./src/pages/singup/singup.js";
import categoryList from "./src/pages/admin/category/category-list.js";
import registerCategory from "./src/pages/admin/category/register-category.js";
import updateCategory from "./src/pages/admin/category/update-category.js";
import gameList from "./src/pages/admin/game/game-list.js";
import registerGame from "./src/pages/admin/game/register-game.js";
import updateGame from "./src/pages/admin/game/update-game.js";

const root = document.getElementById("root");

const r = router({
  "/": () => home(),
  "/catalog": (props) => catalog(props),
  "/search": () => search(),
  "/details": (props) => details(props),
  "/login": () => login(),
  "/singup": () => singup(),
  "/categories": () => categoryList(),
  "/register-category": () => registerCategory(),
  "/update-category": (props) => updateCategory(props),
  "/games": () => gameList(),
  "/register-game": () => registerGame(),
  "/update-game": (props) => updateGame(props),
  "/404": () => div("Página não encontrada"),
});

root.appendChild(r);

const refresh = () => {
	r.refresh()
};

export { refresh }