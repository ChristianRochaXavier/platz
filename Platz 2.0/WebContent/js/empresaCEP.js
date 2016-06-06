$(document).ready(

		function() {

			// Variáveis
			var txtCep = $("#form-empresa\\:txtCep");
			var rua = $("#input_form-empresa\\:txtRua");
			var bairro = $("#input_form-empresa\\:txtBairro");
			var cidade = $("#input_form-empresa\\:txtCidade");
			var estado = $("#input_form-empresa\\:txtEstado");

			function limparCep() {
				// Limpa valores do formulário de cep.
				rua.val("");
				bairro.val("");
				cidade.val("");
				estado.val("");
			}

			// Quando o campo cep perde o foco.
			txtCep.blur(function() {

				// Nova variável "cep" somente com dígitos.
				var cep = $(this).val().replace(/\D/g, '');

				// Verifica se campo cep possui valor informado.
				if (cep != "") {

					// Expressão regular para validar o CEP.
					var validacep = /^[0-9]{8}$/;

					// Valida o formato do CEP.
					if (validacep.test(cep)) {

						// Preenche os campos com "..." enquanto
						// consulta o webservice.
						rua.val("...");
						bairro.val("...");
						cidade.val("...");
						estado.val("...");

						// Consulta o webservice viacep.com.br/
						$.getJSON("//viacep.com.br/ws/" + cep
								+ "/json/?callback=?", function(dados) {

							if (!("erro" in dados)) {
								// Atualiza os campos com os valores da
								// consulta.
								rua.val(dados.logradouro);
								bairro.val(dados.bairro);
								cidade.val(dados.localidade);
								estado.val(dados.uf);

								// Deixa os campos como apenas leitura
								rua.attr('readonly', 'true');
								bairro.attr('readonly', 'true');
								cidade.attr('readonly', 'true');
								estado.attr('readonly', 'true');

							} // end if.
							else {
								// CEP pesquisado não foi encontrado.
								limparCep();
								alert("CEP não encontrado.");
							}
						});
					} // end if.
					else {
						// cep é inválido.
						limparCep();
						alert("Formato de CEP inválido.");
					}
				} // end if.
				else {
					// cep sem valor, limpa formulário.
					limparCep();
				}
			});
		});