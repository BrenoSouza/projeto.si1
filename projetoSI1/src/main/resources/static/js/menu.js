/**
 * 
 */

function clickMenu(){
	var menu = document.getElementById("menu");
	if(menu.style.opacity == 1){
		closeMenu();
	} else {
		openMenu();
	}
}

function openMenu(){
	menu.style.right = "0px"; // return it to active screen space
	menu.style.opacity = 1;
}

function closeMenu(){
	menu.style.opacity = 0;
	menu.style.right = "-350px"; // remove it from active screen space
}