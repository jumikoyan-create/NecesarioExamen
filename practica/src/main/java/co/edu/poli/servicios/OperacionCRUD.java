package co.edu.poli.servicios;
import co.edu.poli.modelo.Mediodetransporte;
public interface OperacionCRUD {
	
	  void crear(Mediodetransporte m);

	    /**
	     * Busca una entidad por su identificador.
	     *
	     * @param id identificador de la entidad
	     * @return entidad encontrada o null si no existe
	     */
	    Mediodetransporte leer(String id);

	    /**
	     * Actualiza la información de una entidad existente.
	     *
	     * @param id identificador de la entidad a actualizar
	     * @param e nueva información de la entidad
	     */
	    void actualizar(String id, Mediodetransporte m);

	    /**
	     * Elimina una entidad del sistema por su ID.
	     *
	     * @param id identificador de la entidad a eliminar
	     */
	    void eliminar(String id);
	    

	    Mediodetransporte[] listar();

}
