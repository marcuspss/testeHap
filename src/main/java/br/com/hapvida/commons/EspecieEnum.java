package br.com.hapvida.commons;

public enum EspecieEnum {
	GATO("gato"), CACHORRO("cachorro"), PASSARINHO("passarinho");

	private final String especie;

	EspecieEnum(String status){
	        this.especie = status;
	    }

	public String getStatus() {
		return especie;
	}
}
