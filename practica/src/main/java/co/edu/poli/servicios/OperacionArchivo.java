package co.edu.poli.servicios;

import co.edu.poli.modelo.Mediodetransporte;

/**
 * Interfaz que define las operaciones de manejo de archivos
 * para objetos de tipo Entidad, incluyendo serialización
 * y deserialización.
 *
 * Permite persistir cualquier objeto del sistema que herede de Entidad.
 */
public interface OperacionArchivo {

    /**
     * Serializa un arreglo de entidades y lo guarda en un archivo.
     *
     * @param entidades arreglo de objetos Entidad a guardar
     * @param path ruta donde se almacenará el archivo
     * @param name nombre del archivo
     * @return mensaje indicando el resultado de la operación
     */
    public String serializar(Mediodetransporte[] transporte, String path, String name);

    /**
     * Deserializa un archivo y recupera un arreglo de entidades.
     *
     * @param path ruta del archivo
     * @param name nombre del archivo
     * @return arreglo de objetos Entidad recuperado
     */
    public Mediodetransporte[] deserializar(String path, String name);
}