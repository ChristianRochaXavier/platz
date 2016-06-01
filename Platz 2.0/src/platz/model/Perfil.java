package platz.model;

public enum Perfil {
	
	ADMINISTRADOR("Administrador"),
	EMPRESA("Empresa"),
	USUARIO("Usuario");
	
	private String label;
	
	public String getLabel() {			
		return label;		
	}

	public void setLabel(String label) {
		this.label = label;
	}

	private Perfil(String label) {
		this.label = label;
	}

}
