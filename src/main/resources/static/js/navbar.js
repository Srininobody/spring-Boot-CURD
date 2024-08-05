$(document).ready(function() {

    $.ajax({
        url: '/active',
        method: 'GET',
        success: function(data) {
            console.log("Menu data: ", data);  // Log the data for debugging
            let menu = buildMenu(data, "0");  // Updated to match the parent type as string
            $('#navbar-menu').append(menu);
        },
        error: function(error) {
            console.log("Error fetching menu data:", error);
        }
    });

    function buildMenu(menuData, parentId) {
        let menuItems = '';
        menuData.forEach(function(menu) {
            if (menu.parent === parentId) {  // Updated to use 'parent'
                let subMenu = buildMenu(menuData, menu.id.toString());  // Convert menu.id to string
                if (subMenu) {
                    menuItems += `
                        <li class="custom-nav-item custom-dropdown">
                            <a class="custom-nav-link" href="#">${menu.manuName}</a>
                            <ul class="custom-dropdown-menu">
                                ${subMenu}
                            </ul>
                        </li>
                    `;
                } else {
                    menuItems += `<li class="custom-nav-item"><a class="custom-nav-link" href="${menu.manuName}">${menu.manuName}</a></li>`;
                }
            }
        });
        return menuItems ? menuItems : '';
    }

    // Toggle navbar on button click
    $('.custom-navbar-toggler').on('click', function() {
        $('.custom-navbar-collapse').toggleClass('show');
    });

});
