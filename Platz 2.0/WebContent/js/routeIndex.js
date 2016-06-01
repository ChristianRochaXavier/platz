		var tipoViagemSelect;
		var travelModeSelect;
		var centro = new google.maps.LatLng(-23.550520,-46.633309);

		window.onload = initialize();
		
		document.getElementById("routeForm").addEventListener("submit",function(ev){

			ev.preventDefault();
			calcRoute();

		});

		var directionDisplay;
		var directionsService = new google.maps.DirectionsService();

		function initialize() {
			
			directionsDisplay = new google.maps.DirectionsRenderer();
			var myOptions = {
				zoom: 15,
				center: centro,
				mapTypeId: google.maps.MapTypeId.ROADMAP,
				mapTypeControl: false
			};
			
			var map = new google.maps.Map(document.getElementById("map_canvas"),myOptions);
			directionsDisplay.setMap(map);

			if (navigator.geolocation) { // Se o navegador do usuário tem suporte ao Geolocation
				navigator.geolocation.getCurrentPosition(function (position) {
				      centro = new google.maps.LatLng(position.coords.latitude, position.coords.longitude); // Com a latitude e longitude que retornam do Geolocation, criamos um LatLng
				      map.setCenter(centro);
				  });
			}
			
			//Adiciona o passo a passo de como chegar no painel
			directionsDisplay.setPanel(document.getElementById("directionsPanel"));
			
			var marker = new google.maps.Marker({
				map: map, 
				title:"Ponto A"
			}); 
		}

		//Calcula Rota
		function calcRoute() {

			tipoViagemSelect = document.getElementById("routeForm:tipoViagem").value;

			//Seleciona o tipo de Viagem
			if (tipoViagemSelect === "DRIVING") {
				travelModeSelect = google.maps.DirectionsTravelMode.DRIVING;
			} else if (tipoViagemSelect === "TRANSIT") {
				travelModeSelect = google.maps.DirectionsTravelMode.TRANSIT;					
			}else{
				travelModeSelect = google.maps.DirectionsTravelMode.DRIVING;
			}

			var start = document.getElementById("routeForm:routeStart").value;
			var end = document.getElementById("routeForm:routeEnd").value;
			var request = {
				origin:start,
				destination:end,
				travelMode: travelModeSelect,			
			};

			directionsService.route(request, function(response, status) {
				if (status == google.maps.DirectionsStatus.OK) {
					directionsDisplay.setDirections(response);
				}
			});
		}