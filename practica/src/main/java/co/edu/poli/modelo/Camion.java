package co.edu.poli.modelo;

public class Camion extends Mediodetransporte{
	String mercancia;
	
	

	public Camion(String id, String marca, String modelo, String capacidad, Encargado encargado, String mercancia) {
		super(id, marca, modelo, capacidad, encargado);
		this.mercancia = mercancia;
	}

	public String getMercancia() {
		return mercancia;
	}

	public void setMercancia(String mercancia) {
		this.mercancia = mercancia;
	}
	

}
