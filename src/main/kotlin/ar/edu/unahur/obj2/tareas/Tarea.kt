package ar.edu.unahur.obj2.tareas

class Tarea (val horasEstimadas : Int,val costoDeInfraestructura : Int,val responsable: Responsable) {
    fun horasNecesariasParaFinalizar() : Int {
       return this.horasEstimadas / responsable.cantDeEmpleados()
    }
    fun costoResponsable () = responsable.cuantoCobraPorHora * horasEstimadas
    //fun costoTotalDeEmpleados () =
    fun costoPorEmpleado () = responsable.empleados.map { it.cuantoCobraPorHora }.forEach{a -> a * this.horasNecesariasParaFinalizar()}
    fun costoTotalInfraestructura () = costoResponsable() + costoDeInfraestructura


}



class Responsable (val empleados: MutableList<Empleados>,val cuantoCobraPorHora :Int){

    fun cantDeEmpleados() = empleados.size


}

