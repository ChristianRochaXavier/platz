var tipoViagemSelect;
var travelModeSelect;

// Centro estático do mapa
var centro = new google.maps.LatLng(-23.550520, -46.633309);

window.onload = initialize();

document.getElementById("routeForm").addEventListener("submit", function(ev) {

	ev.preventDefault();
	calcRoute();
});

// Display das rotas
var directionDisplay;

// Serviço que busca a descrição das rotas
var directionsService = new google.maps.DirectionsService();

// função que inicializa o mapa
function initialize() {

	// Objeto do Google Geocoder
	var geocoder = new google.maps.Geocoder();

	// Geocoder
	geocoder.geocode({
		'address' : $("#localizacaoEvento").val()
	}, function(results, status) {
		// Se o status da busca é ok
		if (status == google.maps.GeocoderStatus.OK) {
			// Set a latitude
			latitude = results[0].geometry.location.lat();
			// Set a longitude
			longitude = results[0].geometry.location.lng();

			//Define o centro do mapa com a localização do evento
			map.setCenter(new google.maps.LatLng(latitude, longitude));

			// Marcador
			var marker = new google.maps.Marker({
				position : new google.maps.LatLng(latitude, longitude),
				title: "O evento está aqui",
				map : map
			});
		}
	});

	// Cria um novo DirectionsRender, que vai mostrar a descrição das rotas
	directionsDisplay = new google.maps.DirectionsRenderer();

	// Opções padrões do mapa
	var myOptions = {
		zoom : 15,
		center : centro,
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		mapTypeControl : true
	};

	// Objeto do tipo mapa
	var map = new google.maps.Map(document.getElementById("map_canvas"),
			myOptions);

	// Definir o mapa para o qual o directions irá trabalhar
	directionsDisplay.setMap(map);

	// Adiciona o passo a passo de como chegar no painel
	directionsDisplay.setPanel(document.getElementById("directionsPanel"));

}

// Calcula Rota
function calcRoute() {

	// Tipo de viagem selecionada
	tipoViagemSelect = document.getElementById("routeForm:tipoViagem").value;

	// Seleciona o tipo de Viagem
	if (tipoViagemSelect === "DRIVING") {
		travelModeSelect = google.maps.DirectionsTravelMode.DRIVING;
	} else if (tipoViagemSelect === "TRANSIT") {
		travelModeSelect = google.maps.DirectionsTravelMode.TRANSIT;
	} else {
		travelModeSelect = google.maps.DirectionsTravelMode.DRIVING;
	}

	// Ponto inicial
	var start = document.getElementById("routeForm:routeStart").value;
	// Localização do evento
	var end = document.getElementById("localizacaoEvento").value;

	// Requisição
	var request = {
		origin : start,
		destination : end,
		travelMode : travelModeSelect,
	};

	directionsService.route(request, function(response, status) {
		if (status == google.maps.DirectionsStatus.OK) {
			directionsDisplay.setDirections(response);
		}
	});
}