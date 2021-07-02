package br.com.hapvida.commons;

public enum StatusEnum {
	ATIVO("ativo"), INATIVO("inativo");

	private final String status;

	StatusEnum(String status){
	        this.status = status;
	    }

	public String getStatus() {
		return status;
	}

}
