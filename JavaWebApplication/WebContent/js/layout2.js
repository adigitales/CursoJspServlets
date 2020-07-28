$(document).ready(main);

var cont = 1;

function main(){
	$('.menu-bar').click(function(){
		if(cont == 1){
			$('.menu').css("display","block");
			cont = 0;
		}else{
			cont = 1;
			$('.menu').css("display","none");

		}
	});

	$('.sub-menu').click(function(){
		$(this).children('.children').slideToggle();

	});
}