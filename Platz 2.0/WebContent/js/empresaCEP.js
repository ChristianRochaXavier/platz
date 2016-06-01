$(document).ready(
		function() {

			function limparCep() {
				// Limpa valores do formulário de cep.
				$("#form-empresa\\:rua").val("");
				$("#form-empresa\\:bairro").val("");
				$("#form-empresa\\:cidade").val("");
			}

			// Quando o campo cep perde o foco.
			$("#form-empresa\\:cep").blur(
					function() {

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
								$("#form-empresa\\:rua").val("...")
								$("#form-empresa\\:bairro").val("...")
								$("#form-empresa\\:cidade").val("...")

								// Consulta o webservice viacep.com.br/
								$.getJSON("//viacep.com.br/ws/" + cep
										+ "/json/?callback=?", function(dados) {

									if (!("erro" in dados)) {
										// Atualiza os campos com os valores da
										// consulta.
										$("#form-empresa\\:rua").val(
												dados.logradouro);
										$("#form-empresa\\:bairro").val(
												dados.bairro);
										$("#form-empresa\\:cidade").val(
												dados.localidade);

										// Deixa os campos como apenas leitura
										$("#form-empresa\\:rua").attr(
												'readonly', 'true');
										$("#form-empresa\\:bairro").attr(
												'readonly', 'true');
										$("#form-empresa\\:cidade").attr(
												'readonly', 'true');

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