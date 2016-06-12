var centro = new google.maps.LatLng(-23.550520, -46.633309);
var matrizDeInputsDeEnderecos = $(".enderecoCompleto");
var eventosEmDestaqueEndereco = [];

for (var i=0; i < matrizDeInputsDeEnderecos.length; i++) {
	eventosEmDestaqueEndereco[i] = matrizDeInputsDeEnderecos[i].value;
}


window.onload = initialize();

function initialize() {

	var myOptions = {
		zoom : 15,
		center : centro,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		mapTypeControl : false
	};

	var map = new google.maps.Map(document.getElementById("map_canvas"),
			myOptions);

	// Se o navegador do usuário tem suporte ao Geolocation
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			// Com a latitude e longitude que retornam do Geolocation, criamos
			// um LatLng
			centro = new google.maps.LatLng(position.coords.latitude,
					position.coords.longitude);
			// Definimos a latitude e longitude acima como centro do mapa
			map.setCenter(centro);
		});
	}

	// Objeto do Google Geocoder
	var geocoder = new google.maps.Geocoder();

	// Geocoder
	for (var i = 0; i < eventosEmDestaqueEndereco.length; i++) {

		geocoder.geocode({
			'address' : eventosEmDestaqueEndereco[i]
		}, function(results, status) {
			// Se o status da busca é ok
			if (status == google.maps.GeocoderStatus.OK) {
				// Set a latitude
				latitude = results[0].geometry.location.lat();
				// Set a longitude
				longitude = results[0].geometry.location.lng();
			
				// Marcador
				var marker = new google.maps.Marker({
					position: new google.maps.LatLng(latitude, longitude),
					map : map
				});		
			
			}
		});

	}

	

}