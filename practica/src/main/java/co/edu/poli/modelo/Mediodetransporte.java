package co.edu.poli.modelo;
import java.io.Serializable;


public abstract class Mediodetransporte implements Serializable {
	String id;
	String marca;
	String modelo;
	String capacidad;
	Encargado encargado;
	
	public Mediodetransporte(String id, String marca, String modelo, String capacidad, Encargado encargado) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.capacidad = capacidad;
		this.encargado = encargado;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public Encargado getEncargado() {
		return encargado;
	}
	public void setEncargado(Encargado encargado) {
		this.encargado = encargado;
	} 
	
	
	

}
