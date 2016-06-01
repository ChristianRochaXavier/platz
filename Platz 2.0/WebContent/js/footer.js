function stickyFooter() {

	var footer = $("footer");

	if ((($(document.body).height() + footer.outerHeight()) < $(window).height() && footer.css("position") == "fixed") || ($(document.body).height() < $(window).height() && footer.css("position") != "fixed")) {

		footer.css({ position: "fixed", bottom: "0px" });

	} else {
		footer.css({ position: "static" });
	}
}


jQuery(document).ready(function($){

	stickyFooter();

	$(window).scroll(stickyFooter);

	$(window).resize(stickyFooter);

	$(window).load(stickyFooter);

});