const ctmenu = () => {

    class ContextMenu {
        constructor({target = null, menuItems = [], mode = "dark"}) {
            this.target = target;
            this.menuItems = menuItems;
            this.mode = mode;
            this.targetNode = this.getTargetNode();
            this.menuItemsNode = this.getMenuItemsNode();
            this.isOpened = false;
        }

        getTargetNode() {
            const nodes = document.querySelectorAll(this.target);

            if (nodes && nodes.length !== 0) {
                return nodes;
            } else {
                console.error(`getTargetNode :: "${this.target}" target not found`);
                return [];
            }
        }

        getMenuItemsNode() {
            const nodes = [];

            if (!this.menuItems) {
                console.error("getMenuItemsNode :: Please enter menu items");
                return [];
            }

            this.menuItems.forEach((data, index) => {
                const item = this.createItemMarkup(data);
                item.firstChild.setAttribute(
                    "style",
                    `animation-delay: ${index * 0.08}s`);

                nodes.push(item);
            });

            return nodes;
        }

        createItemMarkup(data) {
            const button = document.createElement("BUTTON");
            const item = document.createElement("LI");

            button.innerHTML = data.content;
            button.classList.add("contextMenu-button");
            item.classList.add("contextMenu-item");

            if (data.divider) item.setAttribute("data-divider", data.divider);
            item.appendChild(button);

            if (data.events && data.events.length !== 0) {
                Object.entries(data.events).forEach(event => {
                    const [key, value] = event;
                    button.addEventListener(key, value);
                });
            }

            return item;
        }

        renderMenu() {
            const menuContainer = document.createElement("UL");

            menuContainer.classList.add("contextMenu");
            menuContainer.setAttribute("data-theme", this.mode);

            this.menuItemsNode.forEach(item => menuContainer.appendChild(item));

            return menuContainer;
        }

        closeMenu(menu) {
            if (this.isOpened) {
                this.isOpened = false;
                menu.remove();
            }
        }

        init() {
            const contextMenu = this.renderMenu();
            document.addEventListener("click", () => this.closeMenu(contextMenu));
            window.addEventListener("blur", () => this.closeMenu(contextMenu));
            document.addEventListener("contextmenu", e => {
                this.targetNode.forEach(target => {
                    if (!e.target.contains(target)) {
                        contextMenu.remove();
                    }
                });
            });

            this.targetNode.forEach(target => {
                target.addEventListener("contextmenu", e => {
                    e.preventDefault();
                    e.stopPropagation();
                    this.isOpened = true;

                    const {clientX, clientY} = e;
                    document.body.appendChild(contextMenu);

                    const positionY =
                        clientY + contextMenu.scrollHeight >= window.innerHeight ?
                            window.innerHeight - contextMenu.scrollHeight - 20 :
                            clientY;
                    const positionX =
                        clientX + contextMenu.scrollWidth >= window.innerWidth ?
                            window.innerWidth - contextMenu.scrollWidth - 20 :
                            clientX;

                    contextMenu.setAttribute(
                        "style",
                        `--width: ${contextMenu.scrollWidth}px;
          --height: ${contextMenu.scrollHeight}px;
          --top: ${positionY}px;
          --left: ${positionX}px;`);

                });
            });
        }
    }


    const copyIcon = `<svg viewBox="0 0 24 24" width="13" height="13" stroke="currentColor" stroke-width="2.5" style="margin-right: 7px" fill="none" stroke-linecap="round" stroke-linejoin="round" class="css-i6dzq1"><rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect><path d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>`;

    const askIcon = `<svg style="margin-right: 7px" xmlns="http://www.w3.org/2000/svg" width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-help-circle"><circle cx="12" cy="12" r="10"></circle><path d="M9.09 9a3 3 0 0 1 5.83 1c0 2-3 3-3 3"></path><line x1="12" y1="17" x2="12.01" y2="17"></line></svg>`;


    const menuItems = [
        {
            content: `${askIcon} Ask this in chat`,
            events: {
                click: (e) => {
                    var listSlideElement = document.querySelectorAll('.slide-element');

                    listSlideElement.forEach(element => {
                        const rect = element.getBoundingClientRect();
                        if (e.clientX >= rect.left && e.clientX <= rect.right && e.clientY >= rect.top && e.clientY <= rect.bottom) {
                            let textContent = element.querySelector('p').textContent;
                            textContent = "I see this in the slide: " + textContent + ". Can you explain it more?";
                            const event = new CustomEvent('updateTextContent', { detail: { textContent } });
                            document.dispatchEvent(event);
                        }
                    });
                }
                // mouseover: () => console.log("Copy Button Mouseover")
                // You can use any event listener from here
            }
        },
        {
            content: `${copyIcon}Delete`,
            divider: "top" // top, bottom, top-bottom
        }];


    const light = new ContextMenu({
        target: ".target-light",
        mode: "light", // default: "dark"
        menuItems
    });


    light.init();

    const dark = new ContextMenu({
        target: ".target-dark",
        menuItems
    });


    dark.init();

// remove message

    function removeMessage() {
        const message = document.querySelector(".right-click");
        if (message) message.remove();
    }

    window.addEventListener("click", removeMessage);
    window.addEventListener("contextmenu", removeMessage);
}

export default ctmenu;
