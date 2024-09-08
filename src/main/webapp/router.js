import home from "./src/pages/home/home.js";
import catalog from "./src/pages/catalog/catalog.js";
import details from "./src/pages/details/details.js";
import search from "./src/pages/search/search.js";
import login from "./src/pages/login/login.js";
import singup from "./src/pages/singup/singup.js";
import categoryList from "./src/pages/admin/category/category-list.js";
import registerCategory from "./src/pages/admin/category/register-category.js";
import updateCategory from "./src/pages/admin/category/update-category.js";

const root = document.getElementById("root");

let count = 0;

const r = router({
  "/": () => home(),
  "/test": () => {
    return div(
      p("Counter : " + count),
      button("Incrementar").onclick$(() => {
        count += 1;
        r.refresh();
      })
    );
  },
  "/catalog": (props) => catalog(props),
  "/search": () => search(),
  "/details": (props) => details(props),
  "/login": () => login(),
  "/singup": () => singup(),
  "/categories": () => categoryList(() => r.refresh()),
  "/register-category": () => registerCategory(),
  "/update-category": (props) => updateCategory(props),
  "/games": () => div(),
  "/register-game": () => div(),
  "/update-game": (props) => div(),
  "/404": () => div("Página não encontrada"),
});

root.appendChild(r);