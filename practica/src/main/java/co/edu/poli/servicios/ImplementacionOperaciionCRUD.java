package co.edu.poli.servicios;

import co.edu.poli.modelo.Mediodetransporte;
import java.io.*;

public class ImplementacionOperaciionCRUD implements OperacionArchivo, OperacionCRUD {

	    private Mediodetransporte[] transporte = new Mediodetransporte[2];

	    public Mediodetransporte[] getEntidades() {
	        return this.transporte;
	    }

	    public void setEntidades(Mediodetransporte[] transporte) {
	        this.transporte = transporte;
	    }

	    @Override
	    public void crear(Mediodetransporte m) {

	        try {
	            if (m == null) {
	                throw new IllegalArgumentException("No se puede agregar un objeto null");
	            }

	            if (leer(m.getId()) != null) {
	                throw new IllegalArgumentException("Ya existe un camion con ese ID");
	            }

	           
	            for (int i = 0; i < transporte.length; i++) {
	                if (transporte[i] == null) {
	                    transporte[i] = m;
	                    System.out.println("transporte agregado en posición " + i);
	                    return;
	                }
	            }

	            // 2. Si no hay espacio → crecer arreglo
	            Mediodetransporte[] nuevo = new Mediodetransporte[transporte.length + 1];

	            for (int i = 0; i < transporte.length; i++) {
	                nuevo[i] = transporte[i];
	            }

	            nuevo[transporte.length] = m;
	            transporte = nuevo;

	            System.out.println("Arreglo expandido. Entidad agregada en posición " + (transporte.length - 1));

	        } catch (IllegalArgumentException ex) {
	            System.out.println("Error al crear: " + ex.getMessage());
	        }
	    }

	    // =========================
	    // LEER
	    // =========================
	    @Override
	    public Mediodetransporte leer(String id) {

	        try {
	            if (id == null) {
	                throw new IllegalArgumentException("El ID no puede ser nulo");
	            }

	            for (Mediodetransporte m : transporte) {
	                if (m != null && m.getId().equals(id)) {
	                    return m;
	                }
	            }

	        } catch (IllegalArgumentException ex) {
	            System.out.println("Error al leer: " + ex.getMessage());
	        }

	        return null;
	    }

	    // =========================
	    // ACTUALIZAR
	    // =========================
	    @Override
	    public void actualizar(String id, Mediodetransporte nueva) {

	        try {
	            if (id == null || nueva == null) {
	                throw new IllegalArgumentException("ID o entidad no pueden ser nulos");
	            }

	            for (int i = 0; i < transporte.length; i++) {
	                if (transporte[i] != null && transporte[i].getId().equals(id)) {
	                    transporte[i] = nueva;
	                    System.out.println("camion actualizado con ID: " + id);
	                    return;
	                }
	            }

	            System.out.println("No se encontró camion con ID: " + id);

	        } catch (IllegalArgumentException ex) {
	            System.out.println("Error al actualizar: " + ex.getMessage());
	        }
	    }

	    // =========================
	    // ELIMINAR
	    // =========================
	    @Override
	    public void eliminar(String id) {

	        try {
	            if (id == null) {
	                throw new IllegalArgumentException("El ID no puede ser nulo");
	            }

	            for (int i = 0; i < transporte.length; i++) {
	                if (transporte[i] != null && transporte[i].getId().equals(id)) {
	                	transporte[i] = null;
	                    System.out.println("camion eliminado con ID: " + id);
	                    return;
	                }
	            }

	            System.out.println("No se encontró camion con ID: " + id);

	        } catch (IllegalArgumentException ex) {
	            System.out.println("Error al eliminar: " + ex.getMessage());
	        }
	    }

	    // =========================
	    // LISTAR
	    // =========================
	    @Override
	    public Mediodetransporte[] listar() {

	        int count = 0;
	        for (Mediodetransporte m : transporte) {
	            if (m != null) count++;
	        }

	        Mediodetransporte[] limpio = new Mediodetransporte[count];
	        int j = 0;

	        for (Mediodetransporte m : transporte) {
	            if (m != null) limpio[j++] = m;
	        }

	        return limpio;
	    }

	    // =========================
	    // SERIALIZAR
	    // =========================
	    @Override
	    public String serializar(Mediodetransporte[] transporte, String path, String name) {

	        try (FileOutputStream fos = new FileOutputStream(path + name);
	             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

	            oos.writeObject(transporte);
	            return "Archivo creado correctamente";

	        } catch (IOException e) {
	            return "Error al guardar: " + e.getMessage();
	        }
	    }

	    // =========================
	    // DESERIALIZAR
	    // =========================
	    @Override
	    public Mediodetransporte[] deserializar(String path, String name) {

	    	Mediodetransporte[] datos = null;

	        try (FileInputStream fis = new FileInputStream(path + name);
	             ObjectInputStream ois = new ObjectInputStream(fis)) {

	            datos = (Mediodetransporte[]) ois.readObject();

	        } catch (IOException e) {
	            System.out.println("Error IO: " + e.getMessage());

	        } catch (ClassNotFoundException e) {
	            System.out.println("Clase no encontrada: " + e.getMessage());
	        }

	        return datos;
	    }
	}

