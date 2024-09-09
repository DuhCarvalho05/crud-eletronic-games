/* This is a adapted (by Caio Lopes) version of Grecha.js tsoding
 *  Base code on https://github.com/tsoding/grecha.js
 */
function tag(name, ...children) {
  const result = document.createElement(name);
  for (const child of children) {
    if (typeof child === "string") {
      result.appendChild(document.createTextNode(child));
    } else {
      result.appendChild(child);
    }
  }

  result.id$ = function (value) {
    this.setAttribute("id", value);
    return this;
  };

  result.class$ = function (value) {
    this.setAttribute("class", value);
    return this;
  };

  result.style$ = function (value) {
    this.setAttribute("style", value);
    return this;
  };

  result.att$ = function (name, value) {
    this.setAttribute(name, value);
    return this;
  };

  result.onclick$ = function (callback) {
    this.onclick = callback;
    return this;
  };
  
  result.onkeydown$ = function (callback) {
    this.onkeydown = callback;
    return this;
  };

  result.innerHTML$ = function (value) {
    this.innerHTML = value;
    return this;
  };

  return result;
}

const MUNDANE_TAGS = [
  "canvas",
  "h1",
  "h2",
  "h3",
  "p",
  "a",
  "ol",
  "ul",
  "li",
  "div",
  "span",
  "select",
  "option",
  "button",
  "nav",
  "main",
  "section",
  "aside",
  "header",
  "footer",
  "form",
  "table",
  "thead",
  "tbody",
  "th",
  "tr",
  "td",
  "label",
  "textarea",
];
for (let tagName of MUNDANE_TAGS) {
  window[tagName] = (...children) => tag(tagName, ...children);
}

function img(src) {
  return tag("img").att$("src", src);
}

function input(type) {
  return tag("input").att$("type", type);
}

function router(routes) {
  let result = div();

  function syncHash() {
    let hashLocation = document.location.hash.split("#")[1];
    
    if (!hashLocation) {
      hashLocation = "/";
    }
    
    let parts = hashLocation.split("/");
   
   	let params = [];
   
   	if(!(parts.length <= 1)){
		hashLocation = "/"+parts[1]
    	params = parts.slice(2);
	}

    if (!(hashLocation in routes)) {
      // TODO(#2): make the route404 customizable in the router component
      const route404 = "/404";

      console.assert(route404 in routes);
      hashLocation = route404;
    }

    result.replaceChildren(routes[hashLocation](params));

    return result;
  }

  syncHash();

  // TODO(#3): there is way to "destroy" an instance of the router to make it remove it's "hashchange" callback
  window.addEventListener("hashchange", syncHash);

  result.refresh = syncHash;

  return result;
}
